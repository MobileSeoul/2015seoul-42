package com.nodedata.project.subway;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

/**
 * Created by DongIl on 2015-10-22.
 */
public class StationInfo {
    private Context context;
    public StationInfo(Context context)
    {
        this.context = context;
    }
    public String GetSationID(float x, float y)
    {
        try {
            Log.i("StationInfo", x + "," + y);
            ProcessDB.openDBFile(context);
            Cursor cursor = ProcessDB.selectQuery("SELECT sId FROM station_info WHERE sPointX1 < " + x + " and sPointX2 > " + x + " and sPointY1 <" + y + " and sPointY2 > " + y + ";");

            cursor.moveToFirst();
            int cursorIndex = cursor.getColumnIndex("sId");
            String value = cursor.getString(cursorIndex);
            //ProcessDB.closingCursor();
           // ProcessDB.closingDBFile();
            return value;
        }
        catch (Exception e)
        {
            return "0";
        }
        finally {
           ProcessDB.closingCursor();
            ProcessDB.closingDBFile();
        }
    }
}
