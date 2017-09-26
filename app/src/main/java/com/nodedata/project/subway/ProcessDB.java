package com.nodedata.project.subway;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class ProcessDB {
	private static ContentValues updateValue;
	
	public static SQLiteDatabase sqlite_otpDB;
	public static Cursor cursor_otpDB;
	public static String ROOT_DIR_DB;
	public static String DATABASE_NAME;
	
	static final String TAG = "DB_Process";
	public static int firstMonth = 11;
	public static int secondMonth = 12;
	public static int thirdMonth = 1;
	
	public static void openDBFile(Context paramContext) {
	    ROOT_DIR_DB = "/data/data/com.nodedata.project.subway/";
	    DATABASE_NAME = "StationTime.sqlite";
	    long tmplength;
	    
	    File dbFolder = new File(ROOT_DIR_DB + "databases");
	    dbFolder.mkdirs();
	    
	    File dbOutfile = new File(ROOT_DIR_DB + "databases/" + DATABASE_NAME);

	    /*Log.i(TAG, "output file name - " + dbOutfile);
	    Log.i(TAG, "output file length - " + dbOutfile.length());*/
	    tmplength = dbOutfile.length();
	    
	   // if(dbOutfile.length() <= 0) {
	    if(tmplength <= 0) {
	    	/*Log.i(TAG, "First Creating - dbOutfile.length() is 0 or -?");*/
	    	
	    	AssetManager assetManager = paramContext.getResources().getAssets();
	    	
	    	try {
	    		InputStream dbInputStream = assetManager.open(DATABASE_NAME, AssetManager.ACCESS_BUFFER);
	    		long filesize = dbInputStream.available();
	    		byte [] tempdata = new byte[(int)filesize];
	    		dbInputStream.read(tempdata);
	    		dbInputStream.close();
	    		
	    		/*Log.i(TAG, "DATABASE - InputStream process ... OK");*/
	    		
	    		dbOutfile.createNewFile();
	    		FileOutputStream dbFileOutputStream = new FileOutputStream(dbOutfile);
	    		
	    		dbFileOutputStream.write(tempdata);
	    		dbFileOutputStream.close();
	    		
	    		/*Log.i(TAG, "DATABASE - write process ... OK");*/
	    	} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    }
	    
	    sqlite_otpDB = SQLiteDatabase.openOrCreateDatabase(dbOutfile, null);
		
	    if(sqlite_otpDB.isOpen()) {
	    	Log.i(TAG, "Database file open ... OK");
	    }else {
	    	Log.i(TAG, "Database file open ... Fail");
	    }
	}

	public static Cursor selectQuery(String query) {

		cursor_otpDB = sqlite_otpDB.rawQuery(query, null);

		return cursor_otpDB;
	}

	public static void deleteQuery(){

		String query;

		query = "UPDATE usrtb SET name = NULL, password = NULL, pwcnt = 0, date = NULL, flag = 0, phonekey = NULL, decrysharekey = NULL WHERE num = 1";

		sqlite_otpDB.rawQuery(query, null);
	}
	
	public static String getDBItem_String(int currentDay, int selectedMonth, String fieldString) {
	    int cursorIndex = 0;
	    String queryString = null;
	    
	    if(selectedMonth == firstMonth){
	    	queryString = "SELECT " + fieldString + " FROM textdata1";
	    } else if(selectedMonth == secondMonth) {
	    	queryString = "SELECT " + fieldString + " FROM textdata2";
	    } else if(selectedMonth == thirdMonth) {
	    	queryString = "SELECT " + fieldString + " FROM textdata3";
	    }
	     
	    String foundString = "";
	    	    
 	    cursor_otpDB = sqlite_otpDB.rawQuery(queryString, null);

		//cursor_otpDB.moveToPosition(currentDay - 1);
	    
	    cursorIndex = cursor_otpDB.getColumnIndex(fieldString);
	    foundString = cursor_otpDB.getString(cursorIndex);
	    
	    return foundString;
	}
	
	public static int getDBItem_Integer(String fieldString) {
	    int cursorIndex = 0;
	    int foundValue = 0;
	    String queryString = null; 


		queryString = "SELECT "+fieldString+" FROM usrtb";

		cursor_otpDB = sqlite_otpDB.rawQuery(queryString, null);

	    
	    cursorIndex = cursor_otpDB.getColumnIndex(fieldString);
	    foundValue = cursor_otpDB.getInt(cursorIndex);
	    
	    return foundValue;
	}
	
	public static void updateDBFile(int index , String stationCode, String stationName, int chkValue ) {

		int updateResult = -1;
		String strValue = "1";

		updateValue = new ContentValues();
		updateValue.put("index", index);
		updateValue.put("station_code", stationCode);
		updateValue.put("stationName", stationName);
		updateValue.put("chkvalue",chkValue);

		updateResult = sqlite_otpDB.update("path_info", updateValue, "num" + " = ?", new String[]{strValue});
		Log.e("TCP DB updateResult = ", ""+updateResult);


	}
	
	public static void closingCursor() {
	    if(!cursor_otpDB.isClosed()) {
	    	cursor_otpDB.close();

	    }
	}
	
	public static void closingDBFile() {
	    if(sqlite_otpDB.isOpen()) {
	    	sqlite_otpDB.close();

	    }
	}
}
