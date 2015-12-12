package com.example.wagontester;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class PhotoActivity extends Activity implements SurfaceHolder.Callback, PictureCallback{
	
	public static final String EXTRA = "com.example.wagontester.photo_activity.image_path";

	private static final int IS_INVALID = -1;
	private static final int IS_ON_PREVIEW = 0;
	private static final int IS_AUTO_FOCUSING = 1;
	private static final int IS_TAKING_PHOTO = 2;
	private static final int IS_ON_POSTVIEW = 3;
	private static final int IS_SAVING_PHOTO = 4;
	
	private int mStatus = IS_INVALID;
	
	private Camera mCamera;
	private Bitmap mBitmap = null;
	
	private SurfaceView mSurfaceView;
	private SurfaceHolder mSurfaceHolder;
	private ImageView mPostView;
	private ProgressBar mProgressBar;
	
	@SuppressWarnings("deprecation") 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);
		
		mSurfaceView = (SurfaceView)findViewById(R.id.surfaceView);
		mSurfaceHolder = mSurfaceView.getHolder();
		mSurfaceHolder.addCallback(this);
		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); 
			// This method was deprecated in API level 11. 
			// this is ignored, this value is set automatically when needed.
		mPostView = (ImageView)findViewById(R.id.imageView);
		mProgressBar = (ProgressBar)findViewById(R.id.progressBar);
		mProgressBar.setVisibility(View.GONE);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		if (mBitmap != null) {
			mBitmap.recycle();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (mStatus) {
		case IS_ON_PREVIEW:
			mStatus = IS_TAKING_PHOTO;
			mProgressBar.setVisibility(View.VISIBLE);
			mCamera.takePicture(null, null, PhotoActivity.this);
			return true;
		case IS_ON_POSTVIEW:
			new SaveImageTask().execute(0);
			return true;
		}
		return super.onTouchEvent(event);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (mStatus) {
		case IS_ON_PREVIEW:
			Log.v("aidi", String.valueOf(keyCode));
			switch (keyCode) {
			case KeyEvent.KEYCODE_SEARCH: // SCAN键
				mStatus = IS_AUTO_FOCUSING;
				Toast.makeText(this, "自动对焦中...", Toast.LENGTH_SHORT).show();
				mCamera.autoFocus(new Camera.AutoFocusCallback() {
					@Override
					public void onAutoFocus(boolean success, Camera camera) {
						Toast.makeText(PhotoActivity.this, "对焦已完成", Toast.LENGTH_SHORT).show();
						mStatus = IS_ON_PREVIEW;
					}
				});
				break;
			case KeyEvent.KEYCODE_BACK:
				mStatus = IS_INVALID;
				setResult(RESULT_CANCELED);
				finish();
				break;
			case KeyEvent.KEYCODE_MENU:
				mStatus = IS_TAKING_PHOTO;
				mProgressBar.setVisibility(View.VISIBLE);
				mCamera.takePicture(null, null, PhotoActivity.this);
				break;
			}
			break;
		case IS_ON_POSTVIEW:
			switch (keyCode) {
			case KeyEvent.KEYCODE_BACK:
				mPostView.setVisibility(View.GONE);
				mPostView.setImageDrawable(null);
				mBitmap.recycle();
				mBitmap = null;
            	mCamera.startPreview();
            	mStatus = IS_ON_PREVIEW;
				break;
			case KeyEvent.KEYCODE_MENU:
				new SaveImageTask().execute(0);
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		
		// Get the original photo
		BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap photo = BitmapFactory.decodeByteArray(data,0,data.length,options);
        
        // Rotate
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        mBitmap = Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), matrix, true); 
        
        // Post view
        mPostView.setImageBitmap(mBitmap);
        mPostView.setVisibility(View.VISIBLE);
        
        mProgressBar.setVisibility(View.INVISIBLE);
        mStatus = IS_ON_POSTVIEW;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		List<Camera.Size> sizes;
		
		Camera.Parameters parameters = mCamera.getParameters();
		sizes = parameters.getSupportedPictureSizes();
		parameters.setPictureSize(sizes.get(0).width, sizes.get(0).height);
		sizes = parameters.getSupportedPreviewSizes();
		parameters.setPreviewSize(sizes.get(0).width, sizes.get(0).height);
		parameters.setPictureFormat(ImageFormat.JPEG);
		parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
		parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
		mCamera.setParameters(parameters);
		mCamera.setDisplayOrientation(90);
		mCamera.startPreview();
		mStatus = IS_ON_PREVIEW;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mCamera = Camera.open();

		try {
			mCamera.setPreviewDisplay(holder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mCamera.stopPreview();
		mCamera.release();
		mCamera = null;
	}

	class SaveImageTask extends AsyncTask<Integer, Integer, String>{

		@Override
		protected String doInBackground(Integer... arg0) {
			String filename = getFilesDir().getPath() + "/photo_" + 
	                  DateFormat.format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".jpg";
			
			try {
				FileOutputStream fOut = new FileOutputStream(filename);
				mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
				
				fOut.flush();
				fOut.close();
			} catch (IOException e) {
				e.printStackTrace();
				Toast.makeText(PhotoActivity.this, "图片保存出错...", Toast.LENGTH_SHORT).show();
				return null;
			}
			
			return filename;
		}
		
		@Override
		public void onPreExecute() {
			mProgressBar.setVisibility(View.VISIBLE);
			mStatus = IS_SAVING_PHOTO;
		}
		
		@Override
		public void onPostExecute(String filename) {
			mProgressBar.setVisibility(View.INVISIBLE);
			mStatus = IS_INVALID;
			
			if(filename != null) {
				setResult(RESULT_OK, new Intent().putExtra(EXTRA , filename));
				finish();
			}
		}
	}
}

