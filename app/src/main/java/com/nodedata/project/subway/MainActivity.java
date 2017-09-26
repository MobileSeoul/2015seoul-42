package com.nodedata.project.subway;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements View.OnTouchListener {

// these matrices will be used to move and zoom image
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
// we can be in one of these 3 states
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
// remember some things for zooming
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;
    private float[] lastEvent = null;

    private static final int REQUEST_CODE = 0;
    //private OverView overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView view = (ImageView) findViewById(R.id.imageView);
        view.setImageResource(R.mipmap.linemapnaver);
        view.setOnTouchListener(this);

        float scale = 1.5f;
        matrix.postScale(scale, scale, mid.x, mid.y);
        view.setImageMatrix(matrix);

    }
    public boolean onTouch(View v, MotionEvent event) {
// handle touch events here
        ImageView view = (ImageView) v;
        Intent it;

        float[] value = new float[9];
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                mode = DRAG;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                lastEvent = new float[4];
                lastEvent[0] = event.getX(0);
                lastEvent[1] = event.getX(1);
                lastEvent[2] = event.getY(0);
                lastEvent[3] = event.getY(1);
                d = rotation(event);
                break;
            case MotionEvent.ACTION_UP:
            matrix.getValues(value);
                float imageX = view.getDrawable().getBounds().width();
                float imageY = view.getDrawable().getBounds().height();
                StationInfo sInfo = new StationInfo(getApplicationContext());
                String sId = sInfo.GetSationID(((event.getX() / value[0]) - (value[2] / value[0])) / imageX, ((event.getY() / value[4]) - (value[5] / value[4])) / imageY);
                if(sId.equals("0")) {

                } else {
                    matrix.postTranslate(-(event.getX() - 600f), -(event.getY() - 480f));
                    view.setImageMatrix(matrix);
                }
                if(sId != "0") {
                    it = new Intent(MainActivity.this, DetailMainActivity.class);
                    it.putExtra("sId",sId);
                    startActivityForResult(it, REQUEST_CODE);
                    overridePendingTransition(R.anim.fade, R.anim.hold);
                }
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    matrix.set(savedMatrix);
                    float dx = event.getX() - start.x;
                    float dy = event.getY() - start.y;
                    matrix.postTranslate(dx, dy);
                    /*Log.i("main1", matrix.toString());*/
                    //이미지 범위 계산용
                    /*matrix.getValues(value);
                    if(value[2]> 100 || value[2] < -view.getMaxWidth()) {
                        dx = -value[2];
                        matrix.postTranslate(dx, 0);
                    }
                    if(value[5]  > 100 || value[5] < -view.getMaxHeight()) {
                        dy = -value[5];
                        matrix.postTranslate(0, dy);
                    }*/
                } else if (mode == ZOOM) {
                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        float[] oldValue = new float[9];
                        matrix.getValues(oldValue);
                        matrix.set(savedMatrix);
                        float scale = (newDist / oldDist);
                        matrix.postScale(scale, scale, mid.x, mid.y);
                        matrix.getValues(value);
                        if(value [0] < 1 || value[0] > 7)
                        {
                            matrix.setValues(oldValue);
                        }
                    }
                    if (lastEvent != null && event.getPointerCount() == 3) {
                        newRot = rotation(event);
                        float r = newRot - d;
                        float[] values = new float[9];
                        matrix.getValues(values);
                        float tx = values[2];
                        float ty = values[5];
                        float sx = values[0];
                        float xc = (view.getWidth() / 2) * sx;
                        float yc = (view.getHeight() / 2) * sx;
                        matrix.postRotate(r, tx + xc, ty + yc);
                    }
                }
                break;
        }
        view.setImageMatrix(matrix);

        return true;
    }

/**
 * Determine the space between the first two fingers
 */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float)Math.sqrt(x * x + y * y);
    }

/**
 * Calculate the mid point of the first two fingers
 */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

/**
 * Calculate the degree to be rotated by.
 *
 * @param event
 * @return Degrees
 */
    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intentDetatileView = new Intent(MainActivity.this, DetailViewActivity.class);
            String strArrayStationCode = "2521,2523,2524,0236,0237,0238,0239,1264,1263,1263,1262,2530,2529,2528,2527,4115,4116";
            intentDetatileView.putExtra("strArrayStationCode", strArrayStationCode);
            startActivity(intentDetatileView);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode){
            case REQUEST_CODE:
                if(requestCode == 0 && intent != null){

                    String start = intent.getStringExtra("start");

                    String end = intent.getStringExtra("end");

                    searchStart(start, end);

                }
        }
    }

    public void searchStart(String start, String end){
        Intent intentpath = new Intent(MainActivity.this, ResultPahAcitivity.class);
        intentpath.putExtra("startStation",start);
        intentpath.putExtra("endStation",end);
        //String strArrayStationCode = "2521,2523,2524,0236,0237,0238,0239,1264,1263,1263,1262,2530,2529,2528,2527,4115,4116";
        //intentDetatileView.putExtra("strArrayStationCode", strArrayStationCode);
        startActivity(intentpath);
        Log.i("20151029","test");
    }
}
