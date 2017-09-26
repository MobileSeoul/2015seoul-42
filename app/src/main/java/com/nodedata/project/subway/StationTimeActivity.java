package com.nodedata.project.subway;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by apple on 15. 10. 27..
 */
public class StationTimeActivity extends Activity {

    String[][] strArrayStation;
    String strStationCode;

    String strWeek = "1";
    int intSelectLine = 0;
    int intLineCount = 0;

    Button buttonLine01;
    Button buttonLine02;
    Button buttonLine03;
    Button buttonLine04;

    Button buttonWeekday;
    Button buttonSat;
    Button buttonSun;

    int clickBtn = 0;
    int clickLineBtn = 0;

    int[] tabbtn_click = {R.drawable.line1_tabbtn_sml, R.drawable.line2_tabbtn_sml,R.drawable.line3_tabbtn_sml,R.drawable.line4_tabbtn_sml,R.drawable.line5_tabbtn_sml,
            R.drawable.line6_tabbtn_sml,R.drawable.line7_tabbtn_sml,R.drawable.line8_tabbtn_sml,R.drawable.line9_tabbtn_sml,R.drawable.line10_tabbtn_sml,
            R.drawable.line11_tabbtn_sml,R.drawable.line12_tabbtn_sml,R.drawable.line13_tabbtn_sml,R.drawable.line14_tabbtn_sml,R.drawable.line15_tabbtn_sml,
            R.drawable.line16_tabbtn_sml,R.drawable.line17_tabbtn_sml,R.drawable.line18_tabbtn_sml};

    int[] tabbtn = {R.drawable.line1_black_tabbtn_sml, R.drawable.line2_black_tabbtn_sml,R.drawable.line3_black_tabbtn_sml,R.drawable.line4_black_tabbtn_sml,R.drawable.line5_black_tabbtn_sml,
            R.drawable.line6_black_tabbtn_sml,R.drawable.line7_black_tabbtn_sml,R.drawable.line8_black_tabbtn_sml,R.drawable.line9_black_tabbtn_sml,R.drawable.line10_black_tabbtn_sml,
            R.drawable.line11_black_tabbtn_sml,R.drawable.line12_black_tabbtn_sml,R.drawable.line13_black_tabbtn_sml,R.drawable.line14_black_tabbtn_sml,R.drawable.line15_black_tabbtn_sml,
            R.drawable.line16_black_tabbtn_sml,R.drawable.line17_black_tabbtn_sml,R.drawable.line18_black_tabbtn_sml};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stationtime);

        Intent intent = getIntent();
        strStationCode = intent.getStringExtra("strStationCode");

        //요일 버튼 클릭 - 평일
        buttonWeekday = (Button) findViewById(R.id.buttonWeekday);
        buttonWeekday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strWeek = "1";
                clickBtn = 0;
                init(strStationCode, strWeek);
            }
        });
        //요일 버튼 클릭 - 토요일
        buttonSat = (Button) findViewById(R.id.buttonSat);
        buttonSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strWeek = "2";
                clickBtn = 1;

                init(strStationCode, strWeek);
            }
        });
        //요일 버튼 클릭 - 일요일
        buttonSun = (Button) findViewById(R.id.buttonSun);
        buttonSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strWeek = "3";
                clickBtn = 2;
                init(strStationCode, strWeek);
            }
        });



        //호선 버튼 클릭 - 첫 번째
        buttonLine01 = (Button) findViewById(R.id.buttonLine01);
        buttonLine01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intSelectLine = 0;
                clickLineBtn = 0;
                strStationCode = strArrayStation[0][intSelectLine];
                buttonLine01.setBackgroundResource(tabbtn_click[Integer.parseInt(strArrayStation[1][0])]);
                init(strStationCode, strWeek);
            }
        });
        //호선 버튼 클릭 - 두 번째
        buttonLine02 = (Button) findViewById(R.id.buttonLine02);
        buttonLine02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intSelectLine = 1;
                clickLineBtn = 1;
                strStationCode = strArrayStation[0][intSelectLine];
                buttonLine02.setBackgroundResource(tabbtn_click[Integer.parseInt(strArrayStation[1][1])]);
                init(strStationCode, strWeek);
            }
        });
        //호선 버튼 클릭 - 세 번째
        buttonLine03 = (Button) findViewById(R.id.buttonLine03);
        buttonLine03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intSelectLine = 2;
                clickLineBtn = 2;
                strStationCode = strArrayStation[0][intSelectLine];
                buttonLine03.setBackgroundResource(tabbtn_click[Integer.parseInt(strArrayStation[1][2])]);
                init(strStationCode, strWeek);
            }
        });
        //호선 버튼 클릭 - 네 번째
        buttonLine04 = (Button) findViewById(R.id.buttonLine04);
        buttonLine04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intSelectLine = 3;
                clickLineBtn = 3;
                strStationCode = strArrayStation[0][intSelectLine];
                buttonLine04.setBackgroundResource(tabbtn_click[Integer.parseInt(strArrayStation[1][3])]);
                init(strStationCode, strWeek);
            }
        });

        //요일 선택
        Calendar calendar = Calendar.getInstance();
        switch (calendar.get(Calendar.DAY_OF_WEEK))
        {
            case 1:
                strWeek = "3";
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                strWeek = "1";
                break;
            case 7:
                strWeek = "2";
                break;
        }

        Button close_button = (Button) findViewById(R.id.buttonTimeBack);
        close_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        DBSelect dbSelect = new DBSelect();
        dbSelect.openDBFile(this);
        strArrayStation = dbSelect.getDBStationLineCount(strStationCode);

        intLineCount = strArrayStation[0].length - 1;
        strArrayStation = getStationName(strArrayStation);

        buttonInit(intLineCount, strArrayStation);
        init(strStationCode, strWeek);
    }

    private String[][] getStationName(String[][] strArrayStation)
    {
        for (int i = 0; i < strArrayStation[1].length; i++) {
            switch (strArrayStation[1][i]) {
                case "1":
                    //strArrayStation[1][i] = "1호선";
                    strArrayStation[1][i] = "0";
                    break;
                case "2":
                    //strArrayStation[1][i] = "2호선";
                    strArrayStation[1][i] = "1";
                    break;
                case "3":
                    //strArrayStation[1][i] = "3호선";
                    strArrayStation[1][i] = "2";
                    break;
                case "4":
                    //strArrayStation[1][i] = "4호선";
                    strArrayStation[1][i] = "3";
                    break;
                case "5":
                    //strArrayStation[1][i] = "5호선";
                    strArrayStation[1][i] = "4";
                    break;
                case "6":
                    //strArrayStation[1][i] = "6호선";
                    strArrayStation[1][i] = "5";
                    break;
                case "7":
                    //strArrayStation[1][i] = "7호선";
                    strArrayStation[1][i] = "6";
                    break;
                case "8":
                    //strArrayStation[1][i] = "8호선";
                    strArrayStation[1][i] = "7";
                    break;
                case "9":
                    //strArrayStation[1][i] = "9호선";
                    strArrayStation[1][i] = "8";
                    break;
                case "B":
                    //strArrayStation[1][i] = "분당선";
                    strArrayStation[1][i] = "9";
                    break;
                case "S":
                    //strArrayStation[1][i] = "신분당선";
                    strArrayStation[1][i] = "10";
                    break;
                case "A":
                    //strArrayStation[1][i] = "공항철도";
                    strArrayStation[1][i] = "11";
                    break;
                case "E":
                    //strArrayStation[1][i] = "에버라인";
                    strArrayStation[1][i] = "12";
                    break;
                case "G":
                    //strArrayStation[1][i] = "경춘선";
                    strArrayStation[1][i] = "13";
                    break;
                case "I":
                    //strArrayStation[1][i] = "인천1호선";
                    strArrayStation[1][i] = "14";
                    break;
                case "K":
                    //strArrayStation[1][i] = "경의중앙";
                    strArrayStation[1][i] = "15";
                    break;
                case "SU":
                    //strArrayStation[1][i] = "수인선";
                    strArrayStation[1][i] = "16";
                    break;
                case "U":
                    //strArrayStation[1][i] = "의정부선";
                    strArrayStation[1][i] = "17";
                    break;
            }
        }
        return strArrayStation;
    }

    private void buttonInit(int intLineCount, String[][] strArrayStation)
    {
        switch (intLineCount) {
            case 0:
                buttonLine01.setVisibility(View.VISIBLE);
                //buttonLine01.setText(strArrayStation[1][0]);
                if(clickLineBtn == 0) {
                    buttonLine01.setBackgroundResource(tabbtn_click[Integer.parseInt(strArrayStation[1][0])]);
                } else {
                    buttonLine01.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][0])]);
                }
                Log.i("20151030", Integer.parseInt(strArrayStation[1][0]) + "  [0]");
                buttonLine02.setVisibility(View.INVISIBLE);
                buttonLine03.setVisibility(View.INVISIBLE);
                buttonLine04.setVisibility(View.INVISIBLE);

                break;
            case 1:
                buttonLine01.setVisibility(View.VISIBLE);
                //buttonLine01.setText(strArrayStation[1][0]);
                //buttonLine01.setBackgroundResource(tabbtn[1]);
                if(clickLineBtn == 0) {
                    buttonLine01.setBackgroundResource(tabbtn_click[Integer.parseInt(strArrayStation[1][0])]);
                    buttonLine02.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][1])]);
                } else if(clickLineBtn == 1){
                    buttonLine01.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][0])]);
                    buttonLine02.setBackgroundResource(tabbtn_click[Integer.parseInt(strArrayStation[1][1])]);
                }
                buttonLine02.setVisibility(View.VISIBLE);
                //buttonLine02.setText(strArrayStation[1][1]);
                //buttonLine02.setBackgroundResource(tabbtn[3]);
                buttonLine03.setVisibility(View.INVISIBLE);
                buttonLine04.setVisibility(View.INVISIBLE);

                break;
            case 2:
                buttonLine01.setVisibility(View.VISIBLE);
                //buttonLine01.setText(strArrayStation[1][0]);
                //buttonLine01.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][0])]);
                if(clickLineBtn == 0) {
                    buttonLine01.setBackgroundResource(tabbtn_click[Integer.parseInt(strArrayStation[1][0])]);
                    buttonLine02.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][1])]);
                    buttonLine03.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][2])]);
                } else if(clickLineBtn == 1){
                    buttonLine01.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][0])]);
                    buttonLine02.setBackgroundResource(tabbtn_click[Integer.parseInt(strArrayStation[1][1])]);
                    buttonLine03.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][2])]);
                } else if(clickLineBtn == 2){
                    buttonLine01.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][0])]);
                    buttonLine02.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][1])]);
                    buttonLine03.setBackgroundResource(tabbtn_click[Integer.parseInt(strArrayStation[1][2])]);
                }
                buttonLine02.setVisibility(View.VISIBLE);
                //buttonLine02.setText(strArrayStation[1][1]);
                //buttonLine02.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][1])]);
                buttonLine03.setVisibility(View.VISIBLE);
                //buttonLine03.setText(strArrayStation[1][2]);
                //buttonLine03.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][2])]);
                buttonLine04.setVisibility(View.INVISIBLE);

                break;
            case 3:
                buttonLine01.setVisibility(View.VISIBLE);
                //buttonLine01.setText(strArrayStation[1][0]);
                if(clickLineBtn == 0) {
                    buttonLine01.setBackgroundResource(tabbtn_click[Integer.parseInt(strArrayStation[1][0])]);
                    buttonLine02.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][1])]);
                    buttonLine03.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][2])]);
                    buttonLine04.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][3])]);
                } else if(clickLineBtn == 1){
                    buttonLine01.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][0])]);
                    buttonLine02.setBackgroundResource(tabbtn_click[Integer.parseInt(strArrayStation[1][1])]);
                    buttonLine03.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][2])]);
                    buttonLine04.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][3])]);
                } else if(clickLineBtn == 2){
                    buttonLine01.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][0])]);
                    buttonLine02.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][1])]);
                    buttonLine03.setBackgroundResource(tabbtn_click[Integer.parseInt(strArrayStation[1][2])]);
                    buttonLine04.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][3])]);
                } else if(clickLineBtn == 3){
                    buttonLine01.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][0])]);
                    buttonLine02.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][1])]);
                    buttonLine03.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][2])]);
                    buttonLine04.setBackgroundResource(tabbtn_click[Integer.parseInt(strArrayStation[1][3])]);
                }
                //buttonLine01.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][0])]);
                buttonLine02.setVisibility(View.VISIBLE);
                //buttonLine02.setText(strArrayStation[1][1]);
                //buttonLine02.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][1])]);
                buttonLine03.setVisibility(View.VISIBLE);
                //buttonLine03.setText(strArrayStation[1][2]);
                //buttonLine03.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][2])]);
                buttonLine04.setVisibility(View.VISIBLE);
                //buttonLine04.setText(strArrayStation[1][3]);
                //buttonLine04.setBackgroundResource(tabbtn[Integer.parseInt(strArrayStation[1][3])]);

                break;
        }
    }

    private void init(String strStationCode, String strWeek)
    {
        Calendar calendar = Calendar.getInstance();
        String strNowTime = calendar.get(Calendar.HOUR_OF_DAY) + ":";
        strNowTime += calendar.get(Calendar.MINUTE) + ":";
        strNowTime += calendar.get(Calendar.SECOND);

        DBSelect dbs = new DBSelect();
        dbs.openDBFile(this);

        ArrayList<String> arrayListBeforeTime = dbs.getDBStationAllTime(strStationCode, strWeek, "1");
        ArrayList<String> arrayListAfterTime = dbs.getDBStationAllTime(strStationCode, strWeek, "2");

        int[] intLayout = {R.layout.item_basic, R.layout.item_all, R.layout.item_left, R.layout.item_right};

        MyDessertAdapter mda = new MyDessertAdapter(this, arrayListBeforeTime, arrayListAfterTime, intLayout);

        ListView listView = (ListView) findViewById(R.id.listViewTime);
        listView.setAdapter(mda);

        Log.d("strNowTime", "strNowTime : " + strNowTime);
        Log.d("TimeData", "TimeData : " + arrayListAfterTime.get(10));

        switch (clickBtn){
            case 0:
                buttonWeekday.setBackgroundResource(R.drawable.day_btn);
                buttonSat.setBackgroundResource(R.drawable.saturday_black_btn);
                buttonSun.setBackgroundResource(R.drawable.sunday_black_btn);
                break;
            case 1:
                buttonWeekday.setBackgroundResource(R.drawable.day_black_btn);
                buttonSat.setBackgroundResource(R.drawable.saturday_btn);
                buttonSun.setBackgroundResource(R.drawable.sunday_black_btn);
                break;
            case 2:
                buttonWeekday.setBackgroundResource(R.drawable.day_black_btn);
                buttonSat.setBackgroundResource(R.drawable.saturday_black_btn);
                buttonSun.setBackgroundResource(R.drawable.sunday_btn);
                break;

        }
        buttonInit(intLineCount, strArrayStation);
    }
}

class MyDessertAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<String> stringArrayListBeforeStation;
    ArrayList<String> stringArrayListAfterStation;
    int[] layout;

    boolean boolBeforeLayout = true;
    boolean boolAfterLayout = true;

    boolean boolBefore = false;
    boolean boolAfter = false;

    public MyDessertAdapter(Context context, ArrayList<String> stringArrayListBeforeStation, ArrayList<String> stringArrayListAfterStation, int[] layout) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.stringArrayListBeforeStation = stringArrayListBeforeStation;
        this.stringArrayListAfterStation = stringArrayListAfterStation;
        this.layout= layout;
    }

    @Override
    public int getCount() {
        if (stringArrayListBeforeStation.size() > stringArrayListBeforeStation.size())
        {
            return stringArrayListBeforeStation.size();
        }
        else
        {
            return stringArrayListBeforeStation.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Calendar calendar = Calendar.getInstance();
        int intNowHour = calendar.get(Calendar.HOUR_OF_DAY) * 3600;
        int intNowMinute = calendar.get(Calendar.MINUTE) * 60;
        int intNowSecond = calendar.get(Calendar.SECOND);

        if (stringArrayListBeforeStation.size() > position && stringArrayListAfterStation.size() > position) {
            //[0]:도착시간, [1]:출발시간, [2]종착역이름, [3]급행여부
            String[] strArrayBeforeStation = stringArrayListBeforeStation.get(position).split(",");
            //도착시간 - [0]:시, [1]:분, [2]초
            String[] strArrayBeforeStationArriveTime = strArrayBeforeStation[0].split(":");
            //출발시간 - [0]:시, [1]:분, [2]초
            String[] strArrayBeforeStationLeaveTime = strArrayBeforeStation[1].split(":");

            //[0]:도착시간, [1]:출발시간, [2]종착역이름, [3]급행여부
            String[] strArrayAfterStation = stringArrayListAfterStation.get(position).split(",");
            //도착시간 - [0]:시, [1]:분, [2]초
            String[] strArrayAfterStationArriveTime = strArrayAfterStation[0].split(":");
            //출발시간 - [0]:시, [1]:분, [2]초
            String[] strArrayAfterStationLeaveTime = strArrayAfterStation[1].split(":");

            int intBeforeHourTemp;
            int intBeforeMinuteTemp;
            int intBeforeSecondTemp;

            int intAfterHourTemp;
            int intAfterMinuteTemp;
            int intAfterSecondTemp;

            if (strArrayBeforeStationArriveTime.equals("00:00:00"))
            {
                intBeforeHourTemp = Integer.parseInt(strArrayBeforeStationLeaveTime[0]) * 3600;
                intBeforeMinuteTemp = Integer.parseInt(strArrayBeforeStationLeaveTime[1]) * 60;
                intBeforeSecondTemp = Integer.parseInt(strArrayBeforeStationLeaveTime[2]);
            }
            else
            {
                intBeforeHourTemp = Integer.parseInt(strArrayBeforeStationArriveTime[0]) * 3600;
                intBeforeMinuteTemp = Integer.parseInt(strArrayBeforeStationArriveTime[1]) * 60;
                intBeforeSecondTemp = Integer.parseInt(strArrayBeforeStationArriveTime[2]);
            }

            if (strArrayAfterStationArriveTime.equals("00:00:00"))
            {
                intAfterHourTemp = Integer.parseInt(strArrayAfterStationLeaveTime[0]) * 3600;
                intAfterMinuteTemp = Integer.parseInt(strArrayAfterStationLeaveTime[1]) * 60;
                intAfterSecondTemp = Integer.parseInt(strArrayAfterStationLeaveTime[2]);
            }
            else
            {
                intAfterHourTemp = Integer.parseInt(strArrayAfterStationArriveTime[0]) * 3600;
                intAfterMinuteTemp = Integer.parseInt(strArrayAfterStationArriveTime[1]) * 60;
                intAfterSecondTemp = Integer.parseInt(strArrayAfterStationArriveTime[2]);
            }

            int intResultBefore = (intNowHour + intNowMinute + intNowSecond) - (intBeforeHourTemp + intBeforeMinuteTemp + intBeforeSecondTemp);

            int intResultAfter = (intNowHour + intNowMinute + intNowSecond) - (intAfterHourTemp + intAfterMinuteTemp + intAfterSecondTemp);

            if (intResultBefore < 0)
            {
                boolBefore = true;
            }

            if (intResultAfter < 0)
            {
                boolAfter = true;
            }

            //convertView = ViewSelect(boolBefore, boolAfter, convertView, parent);
            convertView = inflater.inflate(layout[0], parent, false);

            TextView textViewMinuteLeft = (TextView) convertView.findViewById(R.id.textViewMinuteLeft);
            TextView textViewTopLeft = (TextView) convertView.findViewById(R.id.textViewTopLeft);
            //급행 표시
            if (strArrayBeforeStation[3].equals("Y"))
            {
                textViewTopLeft.setText("(급)" + strArrayBeforeStation[2]);
            }
            else
            {
                textViewTopLeft.setText(strArrayBeforeStation[2]);
            }
            TextView textViewBottomLeft = (TextView) convertView.findViewById(R.id.textViewBottomLeft);
            //'출발역', '종착역', '열차출발'
            if (strArrayBeforeStation[0].equals("00:00:00"))
            {
                textViewMinuteLeft.setText(strArrayBeforeStationLeaveTime[0] + ":" + strArrayBeforeStationLeaveTime[1]);
                textViewBottomLeft.setText("분 [도착역]");
            }
            else if (strArrayBeforeStation[1].equals("00:00:00"))
            {
                textViewMinuteLeft.setText(strArrayBeforeStationArriveTime[0] + ":" + strArrayBeforeStationArriveTime[1]);
                textViewBottomLeft.setText("분 [출발역]");
            }
            else
            {
                textViewMinuteLeft.setText(strArrayBeforeStationLeaveTime[0] + ":" + strArrayBeforeStationLeaveTime[1]);
                textViewBottomLeft.setText("분 열차출발");
            }

            TextView textViewMinuteRigth = (TextView) convertView.findViewById(R.id.textViewMinuteRight);
            TextView textViewTopRight = (TextView) convertView.findViewById(R.id.textViewTopRight);
            //급행 표시
            if (strArrayAfterStation[3].equals("Y"))
            {
                textViewTopRight.setText("(급)" + strArrayAfterStation[2]);
            }
            else
            {
                textViewTopRight.setText(strArrayAfterStation[2]);
            }
            TextView textViewBottomRight = (TextView) convertView.findViewById(R.id.textViewBottomRight);
            //'출발역', '종착역', '열차출발'
            if (strArrayAfterStation[0].equals("00:00:00"))
            {
                textViewMinuteRigth.setText(strArrayAfterStationLeaveTime[0] + ":" + strArrayAfterStationLeaveTime[1]);
                textViewBottomRight.setText("분 [도착역]");
            }
            else if (strArrayAfterStation[1].equals("00:00:00"))
            {
                textViewMinuteRigth.setText(strArrayAfterStationArriveTime[0] + ":" + strArrayAfterStationArriveTime[1]);
                textViewBottomRight.setText("분 [출발역]");
            }
            else
            {
                textViewMinuteRigth.setText(strArrayAfterStationLeaveTime[0] + ":" + strArrayAfterStationLeaveTime[1]);
                textViewBottomRight.setText("분 열차출발");
            }
        }
        else if (stringArrayListBeforeStation.size() > position && stringArrayListAfterStation.size() <= position) {
            //[0]:도착시간, [1]:출발시간, [2]종착역이름, [3]급행여부
            String[] strArrayBeforeStation = stringArrayListBeforeStation.get(position).split(",");
            //도착시간 - [0]:시, [1]:분, [2]초
            String[] strArrayBeforeStationArriveTime = strArrayBeforeStation[0].split(":");
            //출발시간 - [0]:시, [1]:분, [2]초
            String[] strArrayBeforeStationLeaveTime = strArrayBeforeStation[1].split(":");

            int intBeforeHourTemp = Integer.parseInt(strArrayBeforeStationArriveTime[0]) * 3600;
            int intBeforeMinuteTemp = Integer.parseInt(strArrayBeforeStationArriveTime[1]) * 60;
            int intBeforeSecondTemp = Integer.parseInt(strArrayBeforeStationArriveTime[2]);

            int intResultBefore = (intNowHour + intNowMinute + intNowSecond) - (intBeforeHourTemp + intBeforeMinuteTemp + intBeforeSecondTemp);

            if (intResultBefore < 0)
            {
                boolBefore = true;
            }

            //convertView = ViewSelect(boolBefore, boolAfter, convertView, parent);
            convertView = inflater.inflate(layout[0], parent, false);

            TextView textViewMinuteLeft = (TextView) convertView.findViewById(R.id.textViewMinuteLeft);
            TextView textViewTopLeft = (TextView) convertView.findViewById(R.id.textViewTopLeft);
            //급행 표시
            if (strArrayBeforeStation[3].equals("Y"))
            {
                textViewTopLeft.setText("(급)" + strArrayBeforeStation[2]);
            }
            else
            {
                textViewTopLeft.setText(strArrayBeforeStation[2]);
            }
            TextView textViewBottomLeft = (TextView) convertView.findViewById(R.id.textViewBottomLeft);
            //'출발역', '종착역', '열차출발'
            if (strArrayBeforeStation[0].equals("00:00:00"))
            {
                textViewMinuteLeft.setText(strArrayBeforeStationLeaveTime[0] + ":" + strArrayBeforeStationLeaveTime[1]);
                textViewBottomLeft.setText("분 [도착역]");
            }
            else if (strArrayBeforeStation[1].equals("00:00:00"))
            {
                textViewMinuteLeft.setText(strArrayBeforeStationArriveTime[0] + ":" + strArrayBeforeStationArriveTime[1]);
                textViewBottomLeft.setText("분 [출발역]");
            }
            else
            {
                textViewMinuteLeft.setText(strArrayBeforeStationLeaveTime[0] + ":" + strArrayBeforeStationLeaveTime[1]);
                textViewBottomLeft.setText("분 열차출발");
            }

            TextView textViewMinuteRigth = (TextView) convertView.findViewById(R.id.textViewMinuteRight);
            TextView textViewTopRight = (TextView) convertView.findViewById(R.id.textViewTopRight);
            TextView textViewBottomRight = (TextView) convertView.findViewById(R.id.textViewBottomRight);

            textViewMinuteRigth.setText("");
            textViewTopRight.setText("");
            textViewBottomRight.setText("");
        }
        else if (stringArrayListAfterStation.size() > position && stringArrayListBeforeStation.size() <= position)
        {
            //[0]:도착시간, [1]:출발시간, [2]종착역이름, [3]급행여부
            String[] strArrayAfterStation = stringArrayListAfterStation.get(position).split(",");
            //도착시간 - [0]:시, [1]:분, [2]초
            String[] strArrayAfterStationArriveTime = strArrayAfterStation[0].split(":");
            //출발시간 - [0]:시, [1]:분, [2]초
            String[] strArrayAfterStationLeaveTime = strArrayAfterStation[1].split(":");

            int intAfterHourTemp = Integer.parseInt(strArrayAfterStationArriveTime[0]) * 3600;
            int intAfterMinuteTemp = Integer.parseInt(strArrayAfterStationArriveTime[1]) * 60;
            int intAfterSecondTemp = Integer.parseInt(strArrayAfterStationArriveTime[2]);

            int intResultAfter = (intNowHour + intNowMinute + intNowSecond) - (intAfterHourTemp + intAfterMinuteTemp + intAfterSecondTemp);

            if (intResultAfter < 0)
            {
                boolAfter = true;
            }

            //convertView = ViewSelect(boolBefore, boolAfter, convertView, parent);
            convertView = inflater.inflate(layout[0], parent, false);

            TextView textViewMinuteRigth = (TextView) convertView.findViewById(R.id.textViewMinuteRight);
            TextView textViewTopRight = (TextView) convertView.findViewById(R.id.textViewTopRight);
            //급행 표시
            if (strArrayAfterStation[3].equals("Y"))
            {
                textViewTopRight.setText("(급)" + strArrayAfterStation[2]);
            }
            else
            {
                textViewTopRight.setText(strArrayAfterStation[2]);
            }
            TextView textViewBottomRight = (TextView) convertView.findViewById(R.id.textViewBottomRight);
            //'출발역', '종착역', '열차출발'
            if (strArrayAfterStation[0].equals("00:00:00"))
            {
                textViewMinuteRigth.setText(strArrayAfterStationLeaveTime[0] + ":" + strArrayAfterStationLeaveTime[1]);
                textViewBottomRight.setText("분 [도착역]");
            }
            else if (strArrayAfterStation[1].equals("00:00:00"))
            {
                textViewMinuteRigth.setText(strArrayAfterStationArriveTime[0] + ":" + strArrayAfterStationArriveTime[1]);
                textViewBottomRight.setText("분 [출발역]");
            }
            else
            {
                textViewMinuteRigth.setText(strArrayAfterStationLeaveTime[0] + ":" + strArrayAfterStationLeaveTime[1]);
                textViewBottomRight.setText("분 열차출발");
            }

            TextView textViewMinuteLeft = (TextView) convertView.findViewById(R.id.textViewMinuteLeft);
            TextView textViewTopLeft = (TextView) convertView.findViewById(R.id.textViewTopLeft);
            TextView textViewBottomLeft = (TextView) convertView.findViewById(R.id.textViewBottomLeft);

            textViewMinuteLeft.setText("");
            textViewTopLeft.setText("");
            textViewBottomLeft.setText("");
        }
        else
        {
            convertView = inflater.inflate(layout[0], parent, false);

            TextView textViewMinuteLeft = (TextView) convertView.findViewById(R.id.textViewMinuteLeft);
            TextView textViewTopLeft = (TextView) convertView.findViewById(R.id.textViewTopLeft);
            TextView textViewBottomLeft = (TextView) convertView.findViewById(R.id.textViewBottomLeft);

            textViewMinuteLeft.setText("");
            textViewTopLeft.setText("");
            textViewBottomLeft.setText("");

            TextView textViewMinuteRigth = (TextView) convertView.findViewById(R.id.textViewMinuteRight);
            TextView textViewTopRight = (TextView) convertView.findViewById(R.id.textViewTopRight);
            TextView textViewBottomRight = (TextView) convertView.findViewById(R.id.textViewBottomRight);

            textViewMinuteRigth.setText("");
            textViewTopRight.setText("");
            textViewBottomRight.setText("");
        }

        return convertView;
    }

    private View ViewSelect(boolean boolBefore, boolean boolAfter, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            return inflater.inflate(layout[0], parent, false);
        }

        if (boolBefore && boolAfter)
        {
            if (boolBeforeLayout) {
                boolBeforeLayout = false;
                return inflater.inflate(layout[1], parent, false);
            }
            else {
                return inflater.inflate(layout[0], parent, false);
            }
        }
        else if (boolBefore && !boolAfter)
        {
            if (boolBeforeLayout) {
                boolBeforeLayout = false;
                return inflater.inflate(layout[2], parent, false);
            }
            else {
                return inflater.inflate(layout[0], parent, false);
            }
        }
        else if (boolAfter && !boolBefore)
        {
            if (boolAfterLayout) {
                boolAfterLayout = false;
                return inflater.inflate(layout[3], parent, false);
            }
            else {
                return inflater.inflate(layout[0], parent, false);
            }
        }
        else
        {
            return inflater.inflate(layout[0], parent, false);
        }
    }
}