package com.example.wagontester;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class PhotoActivity extends Activity implements SurfaceHolder.Callback, PictureCallback{
	
	public static final String EXTRA = "com.example.wagontester.photo_activity.image_path";

	private Camera mCamera;
	private Bitmap mBitmap = null;
	private boolean isOnPostview = false;
	private boolean isBusy;
	
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
		
		setIfBusy(false);
	}
		
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(isBusy) {
			return true;
		}
		
		switch (keyCode) {
		case KeyEvent.KEYCODE_SYSRQ: // KEYCODE_SCAN
			if(!isOnPostview) {
				Toast.makeText(this, "自动对焦中...", Toast.LENGTH_SHORT).show();
				mCamera.autoFocus(new Camera.AutoFocusCallback() {
					@Override
					public void onAutoFocus(boolean success, Camera camera) {
						Toast.makeText(PhotoActivity.this, "对焦已完成", Toast.LENGTH_SHORT).show();
					}
				});
			}
			break;
		case KeyEvent.KEYCODE_BACK:
			if(isOnPostview) {
				mPostView.setVisibility(View.GONE);
				mPostView.setImageDrawable(null);
				mBitmap.recycle();
				
				isOnPostview = false;
            	mCamera.startPreview();
			} else {
				setResult(RESULT_CANCELED);
				finish();
			}
            break;
		case KeyEvent.KEYCODE_ENTER:
			if(isOnPostview) {
//				new SaveImageTask().execute(0);
			} else {
				isOnPostview = true;
				setIfBusy(true);
				mCamera.takePicture(null, null, PhotoActivity.this);
			}
			break;
		}
		return true;
	}
	
	private void setIfBusy(boolean b) {
		isBusy = b;
		if (isBusy) {
			mProgressBar.setVisibility(View.VISIBLE);
		} else {
			mProgressBar.setVisibility(View.GONE);
		}
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
        
        setIfBusy(false);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Camera.Parameters parameters = mCamera.getParameters();
		parameters.setPreviewSize(640, 480); // Largest
		parameters.setPictureFormat(ImageFormat.JPEG); // Only supporting JPEG
		parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO); // Best by now
		mCamera.setParameters(parameters);
		mCamera.setDisplayOrientation(90);
		mCamera.startPreview();
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
}

//	class SaveImageTask extends AsyncTask<Integer, Integer, String>{
//
//		@Override
//		protected String doInBackground(Integer... arg0) {
//			String filename = PhotoActivity.this.getFilesDir().getPath() + "/temp_" + 
//	                  DateFormat.format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".jpg";
//			
//			try {
//				FileOutputStream fOut = new FileOutputStream(filename);
//				mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
//				
//				fOut.flush();
//				fOut.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//				Toast.makeText(PhotoActivity.this, "图片保存出错...", Toast.LENGTH_SHORT).show();
//				return null;
//			}
//			return filename;
//		}
//		
//		@Override
//		public void onPreExecute() {
//			isKeyLocked = true;
//			mProgressBar.setVisibility(View.VISIBLE);
//		}
//		
//		@Override
//		public void onPostExecute(String r) {
//			mProgressBar.setVisibility(View.GONE);
//			if(r != null) {
//				mPostView.setVisibility(View.GONE);
//				mBitmap.recycle();
//				
//				setResult(RESULT_OK, new Intent().putExtra(INTENT_FIELD , r));
//				finish();
//			} else {
//				isKeyLocked = false;
//			}
//		}
//	}

