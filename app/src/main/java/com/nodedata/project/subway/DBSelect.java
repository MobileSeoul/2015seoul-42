package com.nodedata.project.subway;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class DBSelect {
	private static ContentValues updateValue;
	
	public static SQLiteDatabase sqlite_otpDB;
	public static Cursor cursor_otpDB;
	public static String ROOT_DIR_DB;
	public static String DATABASE_NAME;
	
	static final String TAG = "DB_Process";
	
	public static void openDBFile(Context paramContext) {
	    ROOT_DIR_DB = "/data/data/com.nodedata.project.subway/";
	    DATABASE_NAME = "StationTime.sqlite";
	    long tmplength;
	    
	    File dbFolder = new File(ROOT_DIR_DB + "databases");
	    dbFolder.mkdirs();
	    
	    File dbOutfile = new File(ROOT_DIR_DB + "databases/" + DATABASE_NAME);
	    
	    Log.i(TAG, "output file name - " + dbOutfile);
	    Log.i(TAG, "output file length - " + dbOutfile.length());
	    tmplength = dbOutfile.length();
	    
	   // if(dbOutfile.length() <= 0) {
	    if(tmplength <= 0) {
	    	Log.i(TAG, "First Creating - dbOutfile.length() is 0 or -?");
	    	
	    	AssetManager assetManager = paramContext.getResources().getAssets();
	    	
	    	try {
	    		InputStream dbInputStream = assetManager.open(DATABASE_NAME, AssetManager.ACCESS_BUFFER);
	    		long filesize = dbInputStream.available();
	    		byte [] tempdata = new byte[(int)filesize];
	    		dbInputStream.read(tempdata);
	    		dbInputStream.close();
	    		
	    		Log.i(TAG, "DATABASE - InputStream process ... OK");
	    		
	    		dbOutfile.createNewFile();
	    		FileOutputStream dbFileOutputStream = new FileOutputStream(dbOutfile);
	    		
	    		dbFileOutputStream.write(tempdata);
	    		dbFileOutputStream.close();
	    		
	    		Log.i(TAG, "DATABASE - write process ... OK");
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

	public static String getDBRouteImage(String strBeforeStationCode, String strBeforeNowStationCode, String strNextStationCode) {
		String foundValue = "";
		String queryString = null;

		queryString = "SELECT Image FROM StationTransfer WHERE BeforeStationCode = '" +
				strBeforeStationCode + "' AND BeforeNowStationCode = '" +
				strBeforeNowStationCode + "' AND NextStationCode = '" +
				strNextStationCode + "'";

		Cursor cursor = sqlite_otpDB.rawQuery(queryString, null);

		while (cursor.moveToNext()) {
			foundValue = cursor.getString(0);
		}
		return foundValue;
	}

	public static String getDBRouteText(String strImage) {
		String foundValue = "";
		String queryString = null;

		queryString = "SELECT Route FROM StationTransfer WHERE Image = '" + strImage + "'";

		Cursor cursor = sqlite_otpDB.rawQuery(queryString, null);

		while (cursor.moveToNext()) {
			foundValue = cursor.getString(0);
		}
		return foundValue;
	}

	public static ArrayList<String> getDBStationAllTime(String strStationCode, String strWeek, String strDirection) {
		ArrayList<String> foundValue = new ArrayList<String>();
		String queryString = null;

		if (strDirection.equals("1")) {
			queryString = "SELECT ArriveTime, LeaveTime, DestinationStationName, Express FROM StationTime WHERE StationCode = '" +
					strStationCode + "' AND Week = '" +
					strWeek + "' AND Direction = '" +
					strDirection + "' ORDER BY ArriveTime";
		}
		else {
			queryString = "SELECT ArriveTime, LeaveTime, DestinationStationName, Express FROM StationTime WHERE StationCode = '" +
					strStationCode + "' AND Week = '" +
					strWeek + "' AND Direction = '" +
					strDirection + "' ORDER BY LeaveTime";
		}

		Cursor cursor = sqlite_otpDB.rawQuery(queryString, null);

		while (cursor.moveToNext()) {
			foundValue.add(cursor.getString(0) + "," + cursor.getString(1) + "," + cursor.getString(2) + "," + cursor.getString(3));
		}
		return foundValue;
	}

	public static String[][] getDBStationLineCount(String strStationCode) {
		String[][] foundValue;
		String strStationName = "";
		String queryString = null;

		queryString = "SELECT sName FROM station_info WHERE sid = '" + strStationCode + "'";

		Cursor cursor = sqlite_otpDB.rawQuery(queryString, null);

		while (cursor.moveToNext()) {
			strStationName = cursor.getString(0);
		}

		queryString = "SELECT sid, sLineNumChar FROM station_info WHERE sName = '" + strStationName + "' ORDER BY sLineNumChar";

		cursor = sqlite_otpDB.rawQuery(queryString, null);

		foundValue = new String[2][cursor.getCount()];
		int intCount = 0;
		while (cursor.moveToNext()) {
			foundValue[0][intCount] = cursor.getString(0);
			foundValue[1][intCount] = cursor.getString(1);
			intCount++;
		}


		return foundValue;
	}

	public static String getDBStationCodetoStationLine(String strStationCode) {
		String foundValue = "";
		String queryString = null;

		queryString = "SELECT sLineNumChar FROM station_info WHERE sId = '" + strStationCode + "'";

		Cursor cursor = sqlite_otpDB.rawQuery(queryString, null);

		while (cursor.moveToNext()) {
			foundValue = cursor.getString(0);
		}
		return foundValue;
	}

	public static String getDBStationCodetoStationName(String strStationCode) {
		String foundValue = "";
		String queryString = null;

		queryString = "SELECT sName FROM station_info WHERE sId = '" + strStationCode + "'";

		Cursor cursor = sqlite_otpDB.rawQuery(queryString, null);

		while (cursor.moveToNext()) {
			foundValue = cursor.getString(0);
		}
		return foundValue;
	}

	public static String getDBStationNameLinetoStationCode(String strStationName, String strStationLine) {
		String foundValue = "";
		String queryString = null;

		queryString = "SELECT sId FROM station_info WHERE sName = '" +
				strStationName + "' AND sLineNum = '" +
				strStationLine + "'";

		Cursor cursor = sqlite_otpDB.rawQuery(queryString, null);

		while (cursor.moveToNext()) {
			foundValue = cursor.getString(0);
		}
		return foundValue;
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
