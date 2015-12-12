package com.example.wagontester.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

public class DBProvider extends ContentProvider {

	private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	static {
		sUriMatcher.addURI(DBContract.AUTHORITY, DBContract.UserTable.TABLE_NAME, DBContract.URI_USER);
		sUriMatcher.addURI(DBContract.AUTHORITY, DBContract.UserTable.TABLE_NAME + "/#", DBContract.URI_USER_ITEM);
		sUriMatcher.addURI(DBContract.AUTHORITY, DBContract.DutyTable.TABLE_NAME, DBContract.URI_DUTY);
		sUriMatcher.addURI(DBContract.AUTHORITY, DBContract.DutyTable.TABLE_NAME + "/#", DBContract.URI_DUTY_ITEM);
		sUriMatcher.addURI(DBContract.AUTHORITY, DBContract.PartTable.TABLE_NAME, DBContract.URI_PART);
		sUriMatcher.addURI(DBContract.AUTHORITY, DBContract.PartTable.TABLE_NAME + "/#", DBContract.URI_PART_ITEM);
		sUriMatcher.addURI(DBContract.AUTHORITY, DBContract.FaultTable.TABLE_NAME, DBContract.URI_FAULT);
		sUriMatcher.addURI(DBContract.AUTHORITY, DBContract.FaultTable.TABLE_NAME + "/#", DBContract.URI_FAULT_ITEM);
		sUriMatcher.addURI(DBContract.AUTHORITY, DBContract.ModelTable.TABLE_NAME, DBContract.URI_MODEL);
		sUriMatcher.addURI(DBContract.AUTHORITY, DBContract.ModelTable.TABLE_NAME + "/#", DBContract.URI_MODEL_ITEM);
		sUriMatcher.addURI(DBContract.AUTHORITY, DBContract.TaskTable.TABLE_NAME, DBContract.URI_TASK);
		sUriMatcher.addURI(DBContract.AUTHORITY, DBContract.TaskTable.TABLE_NAME + "/#", DBContract.URI_TASK_ITEM);
		sUriMatcher.addURI(DBContract.AUTHORITY, DBContract.ContentTable.TABLE_NAME, DBContract.URI_CONTENT);
		sUriMatcher.addURI(DBContract.AUTHORITY, DBContract.ContentTable.TABLE_NAME + "/#", DBContract.URI_CONTENT_ITEM);
	}
	
	private static DBHelper mDbHelper;
	private SQLiteDatabase mDatabase;
	
	@Override
	public boolean onCreate() {
		mDbHelper = DBHelper.getInstance(getContext());
		mDatabase = mDbHelper.getWritableDatabase();
		return true;
	}
	
	@Override
	public String getType(Uri uri) {
		int match = sUriMatcher.match(uri);
		
		switch (match) {
		case DBContract.URI_USER:
			return DBContract.TYPE_USER;
		case DBContract.URI_USER_ITEM:
			return DBContract.TYPE_USER_ITEM;
		case DBContract.URI_DUTY:
			return DBContract.TYPE_DUTY;
		case DBContract.URI_DUTY_ITEM:
			return DBContract.TYPE_DUTY_ITEM;
		case DBContract.URI_PART:
			return DBContract.TYPE_PART;
		case DBContract.URI_PART_ITEM:
			return DBContract.TYPE_PART_ITEM;
		case DBContract.URI_FAULT:
			return DBContract.TYPE_FAULT;
		case DBContract.URI_FAULT_ITEM:
			return DBContract.TYPE_FAULT_ITEM;
		case DBContract.URI_MODEL:
			return DBContract.TYPE_MODEL;
		case DBContract.URI_MODEL_ITEM:
			return DBContract.TYPE_MODEL_ITEM;
		case DBContract.URI_TASK:
			return DBContract.TYPE_TASK;
		case DBContract.URI_TASK_ITEM:
			return DBContract.TYPE_TASK_ITEM;
		case DBContract.URI_CONTENT:
			return DBContract.TYPE_CONTENT;
		case DBContract.URI_CONTENT_ITEM:
			return DBContract.TYPE_CONTENT_ITEM;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri.toString());
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// No values
		if (values == null) {
			return null;
		}
		
		// Unknown URI
		switch (sUriMatcher.match(uri)) {
		case DBContract.URI_USER:
		case DBContract.URI_DUTY:
		case DBContract.URI_PART:
		case DBContract.URI_FAULT:
		case DBContract.URI_MODEL:
		case DBContract.URI_TASK:
		case DBContract.URI_CONTENT:
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri.toString());
		}
		
		String table = (String) uri.getPathSegments().get(0);
		long rowId = mDatabase.insert(table, null, values);
		if (rowId > 0) {
			Uri new_uri = ContentUris.withAppendedId(uri, rowId);
			getContext().getContentResolver().notifyChange(uri, null);
			return new_uri;
		}
		
		return null;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		String table = (String) uri.getPathSegments().get(0);
		
		switch (sUriMatcher.match(uri)) {
		case DBContract.URI_USER:
		case DBContract.URI_DUTY:
		case DBContract.URI_PART:
		case DBContract.URI_FAULT:
		case DBContract.URI_MODEL:
		case DBContract.URI_TASK:
		case DBContract.URI_CONTENT:
			break;
		case DBContract.URI_USER_ITEM:
		case DBContract.URI_DUTY_ITEM:
		case DBContract.URI_PART_ITEM:
		case DBContract.URI_FAULT_ITEM:
		case DBContract.URI_MODEL_ITEM:
		case DBContract.URI_TASK_ITEM:
		case DBContract.URI_CONTENT_ITEM:
			selection = BaseColumns._ID + "=" + uri.getPathSegments().get(1) + 
					( (selection == null || selection.isEmpty()) ? "" : (" AND (" + selection + ")") );
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri.toString());
		}
		
		int count = mDatabase.delete(table, selection, selectionArgs);
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
	
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		String table = (String) uri.getPathSegments().get(0);
		
		switch (sUriMatcher.match(uri)) {
		case DBContract.URI_USER:
		case DBContract.URI_DUTY:
		case DBContract.URI_PART:
		case DBContract.URI_FAULT:
		case DBContract.URI_MODEL:
		case DBContract.URI_TASK:
		case DBContract.URI_CONTENT:
			break;
		case DBContract.URI_USER_ITEM:
		case DBContract.URI_DUTY_ITEM:
		case DBContract.URI_PART_ITEM:
		case DBContract.URI_FAULT_ITEM:
		case DBContract.URI_MODEL_ITEM:
		case DBContract.URI_TASK_ITEM:
		case DBContract.URI_CONTENT_ITEM:
			selection = BaseColumns._ID + "=" + uri.getPathSegments().get(1) +
					( (selection == null || selection.isEmpty()) ? "" : (" AND (" + selection + ")") );
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri.toString());
		}
		
		int count = mDatabase.update(table, values, selection, selectionArgs);
		if (count > 0) {
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return count;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		String table = (String) uri.getPathSegments().get(0);
		int match = sUriMatcher.match(uri);
		
		switch (match) {
		case DBContract.URI_USER:
		case DBContract.URI_DUTY:
		case DBContract.URI_PART:
		case DBContract.URI_FAULT:
		case DBContract.URI_MODEL:
		case DBContract.URI_TASK:
		case DBContract.URI_CONTENT:
			break;
		case DBContract.URI_USER_ITEM:
		case DBContract.URI_DUTY_ITEM:
		case DBContract.URI_PART_ITEM:
		case DBContract.URI_FAULT_ITEM:
		case DBContract.URI_MODEL_ITEM:
		case DBContract.URI_TASK_ITEM:
		case DBContract.URI_CONTENT_ITEM:
			selection = BaseColumns._ID + "=" + uri.getPathSegments().get(1) +
			( (selection == null || selection.isEmpty()) ? "" : (" AND (" + selection + ")") );
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri.toString());
		}
		
		Cursor c = mDatabase.query(table, projection, selection, selectionArgs, null, null, sortOrder);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

}
