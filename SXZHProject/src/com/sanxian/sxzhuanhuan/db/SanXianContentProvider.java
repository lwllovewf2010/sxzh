/*
 * Copyright (C) Winkee Technologies Co., Ltd. 2005-2012. 
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information 
 * of Winkee Technologies Co., Ltd. ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement 
 * you entered into with Winkee. 
 */
package com.sanxian.sxzhuanhuan.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.net.Uri;
import android.text.TextUtils;

/**
 * 维护数据库
 * 
 * @author 罗治任
 * @date 2012-5-28
 * @version 1.0
 */
public class SanXianContentProvider extends ContentProvider {

	
	public static final String TAG = "SanXianContentProvider";
	private static final String DATABASE_NAME = "sanxian.db";

	private static final int DATABASE_VERSION = 3;

	private static Context mContext;
	private DBOpenHelper mDBOpenHelper;// 数据库维护类(表创建与维护)
	private SQLiteDatabase mDb; // 数据库操作类(表数据的增,删,改,查)

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// 执行删除操作，返回受影响行数
		SqlArguments args = new SqlArguments(uri, selection, selectionArgs);
		int count = mDb.delete(args.table, args.where, args.args);
		// 执行相应操作后，需要通知observer(观察者)，databases产生变化
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public String getType(Uri uri) {
		SqlArguments arguments = new SqlArguments(uri, null, null);
		if (TextUtils.isEmpty(arguments.where)) {
			return "vnd.android.cursor.dir/" + arguments.table;
		} else {
			return "vnd.android.cursor.item/" + arguments.table;
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SqlArguments args = new SqlArguments(uri);
		// 返回新插入行的ID
		long rowId = mDb.replace(args.table, "", values);
		if (rowId > 0) {
			Uri newUri = ContentUris.withAppendedId(uri, rowId);
			// 执行相应操作后，需要通知observer(观察者)，databases产生变化
			getContext().getContentResolver().notifyChange(newUri, null);
			return newUri;
		}
		throw new SQLException("Failed to insert row into " + uri);

	}

	@Override
	public boolean onCreate() {
		mContext = getContext();
		mDBOpenHelper = new DBOpenHelper(mContext, DATABASE_NAME, null,
				DATABASE_VERSION);
		try {
			mDb = mDBOpenHelper.getWritableDatabase();
		} catch (Exception e) {
			mDb = mDBOpenHelper.getReadableDatabase();
		}
		if (mDb != null) {
			
			return true;
		} else {
			
			return false;
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SqlArguments args = new SqlArguments(uri, selection, selectionArgs);
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(args.table);
		Cursor cursor = qb.query(mDb, projection, selection, args.args, null,
				null, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SqlArguments args = new SqlArguments(uri, selection, selectionArgs);
		// 返回受影响行数
		int count = mDb.update(args.table, values, args.where, args.args);
		// 执行相应操作后，需要通知observer(观察者)，databases产生变化
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	/**
	 * 关系表处理类，对表进行分割
	 * 
	 * @author: 罗治任
	 * @version: 2011-12-13 上午11:29:28
	 */
	static class SqlArguments {
		public final String table;
		public final String where;
		public final String[] args;

		SqlArguments(Uri url, String where, String[] args) {
			if (url.getPathSegments().size() == 1) {
				this.table = url.getPathSegments().get(0);
				this.where = where;
				this.args = args;
			} else if (url.getPathSegments().size() != 2) {
				throw new IllegalArgumentException("Invalid URI: " + url);
			} else if (!TextUtils.isEmpty(where)) {
				throw new UnsupportedOperationException(
						"WHERE clause not supported: " + url);
			} else {
				this.table = url.getPathSegments().get(0);
				this.where = "_id=" + ContentUris.parseId(url);
				this.args = null;
			}
		}

		SqlArguments(Uri url) {
			if (url.getPathSegments().size() == 1) {
				table = url.getPathSegments().get(0);
				where = null;
				args = null;
			} else {
				throw new IllegalArgumentException("Invalid URI: " + url);
			}
		}
	}

	/**
	 * 维护表的创建、升级
	 * 
	 * @author: 罗治任
	 * @version: 2011-12-13 上午11:11:21
	 */
	private static class DBOpenHelper extends SQLiteOpenHelper {

		public DBOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);

		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			try {
				db.beginTransaction();
				// 点对点聊天记录表
				db.execSQL("CREATE TABLE "
						+ Chatlog.TABLE_NAME_EMPLOYEECHATLOG + " ("
						+ Chatlog.ID
						+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ Chatlog.CONTENT + " TEXT NOT NULL,"
						+ Chatlog.SENDER + " INTEGER NOT NULL,"
						+ Chatlog.FONT_FAMILY + " TEXT  NULL,"
						+ Chatlog.FONT_SIZE + " INTEGER  NULL,"
						+ Chatlog.FONT_COLOR + " TEXT  NULL,"
						+ Chatlog.SENT_TIME + " TEXT NOT NULL,"
						+ Chatlog.NAME + " TEXT NOT NULL,"
						+ Chatlog.JID + " TEXT NOT NULL,"
						+ Chatlog.HAS_BEEN_READ + " INTEGER NOT NULL,"
						+ Chatlog.LOGON_USER + " TEXT NOT NULL,"
						+ Chatlog.IS_VOICE_MESSAGE +" INTEGER NULL,"
						+ Chatlog.VOICE_MESSAGE_PATH+" TEXT NULL , "
						+ Chatlog.VOICE_MESSAGE_LENGTH + " INTEGER NULL "
						+ ");");
				// //////////////////////////////////////////创建触发器//////////////////////////////////////////////////

				db.setTransactionSuccessful();
				db.endTransaction();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			try {
				db.beginTransaction();
				
				db.execSQL(" DROP TABLE IF EXISTS "
						+ Chatlog.TABLE_NAME_EMPLOYEECHATLOG);
				
				db.setTransactionSuccessful();
			} catch (Exception e) {
				e.printStackTrace();
			}
			db.endTransaction();
			onCreate(db);
		}
	}
}
