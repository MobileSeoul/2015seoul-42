package com.nodedata.project.subway;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.util.Log;
import android.widget.TextView;
import android.content.Context;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by yeoinhyo on 15. 10. 23..
 */
public class DetailMainActivity extends Activity implements View.OnClickListener {

    Button detiailinfo_btn;
    Button time_btn;
    Button bookmark_btn;
    Button startstation_btn;
    Button endstation_btn;
    Button passstation_btn;
    Button mainstation_btn;

    ImageView mainsta_line1_btn;
    ImageView mainsta_line2_btn;
    ImageView mainsta_line3_btn;
    ImageView mainsta_line4_btn;

    TextView mainsta_txtview;

    int flagBtn1 = 0;
    int flagBtn2 = 0;
    int flagBtn3 = 0;
    int flagBtn4 = 0;
    int flagDivideLine = 0;

    int before_cnt = 0;
    int after_cnt = 0;
    String before_station1_code = null;
    String before_station1 = null;
    String before_station2_code = null;
    String before_station2 = null;
    String after_station1_code = null;
    String after_station1 = null;
    String after_station2_code = null;
    String after_station2 = null;
    String main_station_code = null;
    String main_station = null;

    ArrayList<SubwayTime> subwayTimeArrayList;
    SubwayTime subwaytime;

    int[] line_btn = {R.drawable.line1_tabbtn_sml, R.drawable.line2_tabbtn_sml, R.drawable.line3_tabbtn_sml, R.drawable.line4_tabbtn_sml,
            R.drawable.line5_tabbtn_sml, R.drawable.line6_tabbtn_sml, R.drawable.line7_tabbtn_sml, R.drawable.line8_tabbtn_sml,
            R.drawable.line9_tabbtn_sml,R.drawable.line10_tabbtn_sml,R.drawable.line11_tabbtn_sml,R.drawable.line12_tabbtn_sml,
            R.drawable.line13_tabbtn_sml,R.drawable.line14_tabbtn_sml,R.drawable.line15_tabbtn_sml,R.drawable.line16_tabbtn_sml,
            R.drawable.line17_tabbtn_sml,R.drawable.line18_tabbtn_sml};

    int[] line_btn_click = {R.drawable.line1_black_tabbtn_sml, R.drawable.line2_black_tabbtn_sml, R.drawable.line3_black_tabbtn_sml, R.drawable.line4_black_tabbtn_sml,
            R.drawable.line5_black_tabbtn_sml, R.drawable.line6_black_tabbtn_sml, R.drawable.line7_black_tabbtn_sml, R.drawable.line8_black_tabbtn_sml,
            R.drawable.line9_black_tabbtn_sml,R.drawable.line10_black_tabbtn_sml,R.drawable.line11_black_tabbtn_sml,R.drawable.line12_black_tabbtn_sml,
            R.drawable.line13_black_tabbtn_sml,R.drawable.line14_black_tabbtn_sml,R.drawable.line15_black_tabbtn_sml,R.drawable.line16_black_tabbtn_sml,
            R.drawable.line17_black_tabbtn_sml,R.drawable.line18_black_tabbtn_sml

    };

    String[][] leftTimeList = {{null,null},{null,null},{null,null},{null,null},{null,null},{null,null},{null,null}};
    String[][] rightTimeList = {{null,null},{null,null},{null,null},{null,null},{null,null},{null,null},{null,null}};
    String sId;
    Cursor cursor;
    int[] tmplinenum;
    int clickbtn;
    int linecnt;
    ListView listLeft;

    Bundle extra;
    Intent intent;

    String startStationName;
    String endStationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailmain);

        sId = getIntent().getExtras().getString("sId");

        extra = new Bundle();
        intent = new Intent();

        detiailinfo_btn = (Button)findViewById(R.id.detailinfo_btn);
        detiailinfo_btn.setOnClickListener(this);

        time_btn = (Button)findViewById(R.id.time_btn);
        time_btn.setOnClickListener(this);

        bookmark_btn = (Button)findViewById(R.id.bookmark_btn);
        bookmark_btn.setOnClickListener(this);

        startstation_btn = (Button)findViewById(R.id.startstation_btn);
        startstation_btn.setOnClickListener(this);

        endstation_btn = (Button)findViewById(R.id.endstation_btn);
        endstation_btn.setOnClickListener(this);

        passstation_btn = (Button)findViewById(R.id.passstation_btn);
        passstation_btn.setOnClickListener(this);

        mainstation_btn = (Button)findViewById(R.id.mainstation_btn);
        mainstation_btn.setOnClickListener(this);

        mainsta_line1_btn = (ImageView)findViewById(R.id.mainsta_line1_btn);
        mainsta_line1_btn.setOnClickListener(this);
        mainsta_line2_btn = (ImageView)findViewById(R.id.mainsta_line2_btn);
        mainsta_line2_btn.setOnClickListener(this);
        mainsta_line3_btn = (ImageView)findViewById(R.id.mainsta_line3_btn);
        mainsta_line3_btn.setOnClickListener(this);
        mainsta_line4_btn = (ImageView)findViewById(R.id.mainsta_line4_btn);
        mainsta_line4_btn.setOnClickListener(this);

        mainsta_txtview = (TextView)findViewById(R.id.mainsta_txtview);

        listLeft = (ListView)findViewById(R.id.mainsta_time_list);
        //int linecnt = 0;
        main_station = chkMainstationName(sId);
        linecnt = chkLineCnt(main_station);   // sId 역에 몇개의 호선이 지나가는지 체크
        String[] linenum = new String[4];
        linenum = chkLineNum(main_station);  // 지나가는 호선은 몇번 호선인지 체크

        tmplinenum = new int[4];

        for(int i = 0; i < linecnt; i++){

            tmplinenum[i] = Integer.parseInt(linenum[i]);
        }

        clickbtn = 0;


        flagBtn1 = 0;
        clickLineTabBtn(linecnt,clickbtn);
        flagBtn1 = 1;




    }

    public void clickLineTabBtn(int linecnt, int clickbtn){

        int[] tmpBtnImage = new int[4];
        subwayTimeArrayList = new ArrayList<SubwayTime>();
        SubwayTimeAdapter adapter;

        String beforeAfterStation = null;
        chkDivideLine(main_station, tmplinenum[clickbtn]);// 전후 역에 분기 되는 역은 없는지

        if((before_cnt == 2)||(after_cnt == 2)){
            flagDivideLine = 1;
        }

        beforeAfterStation = before_station1 + "  <--  " + main_station + "  -->  " + after_station1;

        mainsta_txtview.setText(beforeAfterStation);

        String nowTime = GetCurrentTime();

        int count1 = makeQuerySubwaytime(main_station, nowTime, "1", "1",tmplinenum[clickbtn]);
        int count2 = makeQuerySubwaytime(main_station, nowTime, "1", "2",tmplinenum[clickbtn]);

        String tmpMin1, tmpMin2;

     /*   if(flagBtn1 == 1) {
            for (int j = 0; j < 7; j++) {
                subwayTimeArrayList.remove(j);
            }
        }*/
        for(int i = 0; i < 7; i++){

            tmpMin1 = chktime(leftTimeList[i][0],nowTime);
            tmpMin2 = chktime(rightTimeList[i][0],nowTime);

            subwaytime = new SubwayTime(leftTimeList[i][1]+"행",tmpMin1+"분후 도착예정","["+leftTimeList[i][0]+" 도착 / 30초 정차]",rightTimeList[i][1]+"행",tmpMin2+"분후 도착예정","["+rightTimeList[i][0]+" 도착 / 30초 정차]");
            subwayTimeArrayList.add(subwaytime);
        }


        adapter= new SubwayTimeAdapter(this, R.layout.item, subwayTimeArrayList);

        listLeft.setAdapter(adapter);
       // listLeft.getAdapter().notifyAll();

        switch (linecnt){
            case 1:
                tmpBtnImage[0] = line_btn[tmplinenum[0]-1];
                tmpBtnImage[1] = R.drawable.noimage;
                tmpBtnImage[2] = R.drawable.noimage;
                tmpBtnImage[3] = R.drawable.noimage;
                if(flagDivideLine == 1){
                    if(clickbtn != 1) {
                        tmpBtnImage[1] = line_btn_click[tmplinenum[0] - 1];
                    }else if(clickbtn == 1){
                        tmpBtnImage[0] = line_btn_click[tmplinenum[0]-1];
                        tmpBtnImage[1] = line_btn[tmplinenum[0] - 1];
                    }
                }
                break;
            case 2:
                tmpBtnImage[0] = line_btn[tmplinenum[0]-1];
                tmpBtnImage[1] = line_btn_click[tmplinenum[1]-1];
                if(clickbtn == 1){
                    tmpBtnImage[0] = line_btn_click[tmplinenum[0]-1];
                    tmpBtnImage[1] = line_btn[tmplinenum[1]-1];
                }
                tmpBtnImage[2] = R.drawable.noimage;
                tmpBtnImage[3] = R.drawable.noimage;
                if(flagDivideLine == 1){
                    if(clickbtn != 2) {
                        tmpBtnImage[2] = line_btn_click[tmplinenum[0] - 1];
                    } else if(clickbtn == 2){
                        tmpBtnImage[2] = line_btn[tmplinenum[0] - 1];
                    }
                }
                break;
            case 3:
                tmpBtnImage[0] = line_btn[tmplinenum[0]-1];
                tmpBtnImage[1] = line_btn_click[tmplinenum[1]-1];
                if(clickbtn == 1){
                    tmpBtnImage[0] = line_btn_click[tmplinenum[0]-1];
                    tmpBtnImage[1] = line_btn[tmplinenum[1]-1];
                    tmpBtnImage[2] = line_btn_click[tmplinenum[2]-1];
                }
                tmpBtnImage[2] = line_btn_click[tmplinenum[2]-1];
                if(clickbtn == 2){
                    tmpBtnImage[0] = line_btn_click[tmplinenum[0]-1];
                    tmpBtnImage[1] = line_btn_click[tmplinenum[1]-1];
                    tmpBtnImage[2] = line_btn[tmplinenum[2]-1];
                }
                tmpBtnImage[3] = R.drawable.noimage;
                if(flagDivideLine == 1){
                    tmpBtnImage[3] = line_btn_click[tmplinenum[0]-1];
                }
                break;
            case 4:
                tmpBtnImage[0] = line_btn[tmplinenum[0]-1];
                tmpBtnImage[1] = line_btn_click[tmplinenum[1]-1];
                if(clickbtn == 1){
                    tmpBtnImage[0] = line_btn_click[tmplinenum[0]-1];
                    tmpBtnImage[1] = line_btn[tmplinenum[1]-1];
                    tmpBtnImage[2] = line_btn_click[tmplinenum[2]-1];
                    tmpBtnImage[3] = line_btn_click[tmplinenum[3]-1];
                }
                tmpBtnImage[2] = line_btn_click[tmplinenum[2]-1];
                if(clickbtn == 2){
                    tmpBtnImage[0] = line_btn_click[tmplinenum[0]-1];
                    tmpBtnImage[1] = line_btn_click[tmplinenum[1]-1];
                    tmpBtnImage[2] = line_btn[tmplinenum[2]-1];
                    tmpBtnImage[3] = line_btn_click[tmplinenum[3]-1];
                }
                tmpBtnImage[3] = line_btn_click[tmplinenum[3]-1];
                if(clickbtn == 3){
                    tmpBtnImage[0] = line_btn_click[tmplinenum[0]-1];
                    tmpBtnImage[1] = line_btn_click[tmplinenum[1]-1];
                    tmpBtnImage[2] = line_btn_click[tmplinenum[2]-1];
                    tmpBtnImage[3] = line_btn[tmplinenum[3]-1];
                }
                break;
            default:
                break;
        }

        mainsta_line1_btn.setBackgroundResource(tmpBtnImage[0]);
        mainsta_line2_btn.setBackgroundResource(tmpBtnImage[1]);
        mainsta_line3_btn.setBackgroundResource(tmpBtnImage[2]);
        mainsta_line4_btn.setBackgroundResource(tmpBtnImage[3]);



    }

    public void onClick(View v){

        switch (v.getId()){
            case R.id.detailinfo_btn:  // 상세 정보 보기
                startActivity(new Intent(DetailMainActivity.this, BookmarkActivity.class));
                overridePendingTransition(R.anim.fade, R.anim.hold);
                finish();
                break;
            case R.id.time_btn:  // 시간표 보기
                Intent intentStationTime = new Intent(DetailMainActivity.this, StationTimeActivity.class);
                intentStationTime.putExtra("strStationCode", sId);
                startActivity(intentStationTime);
                //startActivity(new Intent(DetailMainActivity.this, BookmarkActivity.class));
                //overridePendingTransition(R.anim.fade, R.anim.hold);
                finish();
                break;
            case R.id.bookmark_btn:
                startActivity(new Intent(DetailMainActivity.this, BookmarkActivity.class));
                overridePendingTransition(R.anim.fade, R.anim.hold);
                finish();
                break;
            case R.id.startstation_btn: // 시작역
            //    update_db(sId,main_station,1);
                startStationName = main_station;
                flagBtn2 = 1;
                finish();
                break;
            case R.id.endstation_btn: // 종착역
            //    update_db(sId,main_station,2);
                flagBtn2 = 2;
                endStationName = main_station;
                //extra.putInt("data",1);
                intent.putExtra("start", startStationName);
                intent.putExtra("end", endStationName);
                this.setResult(RESULT_OK, intent);
                // search - start
                this.finish();
                break;
            case R.id.passstation_btn: // 경유역
           //     update_db(sId,main_station,3);
                flagBtn2 = 3;
                finish();
                break;
            case R.id.mainstation_btn: // 닫기
                if(flagBtn2 == 1){
               //     update_db("","",1);
                }else if(flagBtn2 == 3){
               //     update_db("","",1);
               //     update_db("","",2);
               //     update_db("","",3);
                }
                finish();
                break;
            case R.id.mainsta_line1_btn:
                clickLineTabBtn(linecnt, 0);
                //mainsta_line1_btn.setBackgroundResource();
                break;
            case R.id.mainsta_line2_btn:
                clickLineTabBtn(linecnt, 1);
                break;
            case R.id.mainsta_line3_btn:
                clickLineTabBtn(linecnt, 2);
                break;
            case R.id.mainsta_line4_btn:
                clickLineTabBtn(linecnt, 3);
                break;

        }
    }

    public String chkMainstationName(String stationID){

        String nameQuery = null;
        ProcessDB.openDBFile(getApplicationContext());
        nameQuery = "SELECT sName FROM station_info WHERE sId = \""+stationID +"\"";
        Cursor cursor = ProcessDB.selectQuery(nameQuery);
        cursor.moveToFirst();

        int cursorIndex = cursor.getColumnIndex("sName");
        String value = cursor.getString(cursorIndex);

        ProcessDB.closingCursor();
        ProcessDB.closingDBFile();
        return value;
    }

    public int chkLineCnt(String stationName){

        String cntQuery = null;

        ProcessDB.openDBFile(getApplicationContext());
        cntQuery = "SELECT count(*) FROM station_info WHERE sName = \""+stationName +"\"";

        Cursor cursor = ProcessDB.selectQuery(cntQuery);
        cursor.moveToFirst();

        int cursorIndex = cursor.getColumnIndex("count(*)");
        int value = cursor.getInt(cursorIndex);


        ProcessDB.closingCursor();
        ProcessDB.closingDBFile();
        return value;

    }

    public String[] chkLineNum(String stationName){

        String cntQuery = null;

        ProcessDB.openDBFile(getApplicationContext());
        cntQuery = "SELECT sLineNum FROM station_info WHERE sName = \""+stationName+"\"";

        Cursor cursor = ProcessDB.selectQuery(cntQuery);
        cursor.moveToFirst();
        int cursorIndex = cursor.getColumnIndex("sLineNum");
        int cnt = cursor.getCount();
        //cursor.getCount();
        String[] value = new String[4];

        for(int i = 0; i < cnt; i++){
            value[i] = cursor.getString(cursorIndex);
            cursor.moveToNext();

        }

        //int value = cursor.getInt(cursorIndex);


        ProcessDB.closingCursor();
        ProcessDB.closingDBFile();
        return value;


    }
    public void chkDivideLine(String stationName, int lineNum){

        try {
            String divideLineQuery = null;
            divideLineQuery = makeQuery(stationName, lineNum);

            ProcessDB.openDBFile(getApplicationContext());
            Cursor cursor = ProcessDB.selectQuery(divideLineQuery);
            cursor.moveToFirst();

            int cursorIndex = cursor.getColumnIndex("before_cnt");
            before_cnt = cursor.getInt(cursorIndex);
            cursor.moveToFirst();
            cursorIndex = cursor.getColumnIndex("after_cnt");
            after_cnt = cursor.getInt(cursorIndex);
            cursor.moveToFirst();
            cursorIndex = cursor.getColumnIndex("before_station1_code");
            before_station1_code = cursor.getString(cursorIndex);
            cursor.moveToFirst();
            cursorIndex = cursor.getColumnIndex("before_station1");
            before_station1 = cursor.getString(cursorIndex);
            cursor.moveToFirst();
            cursorIndex = cursor.getColumnIndex("before_station_code2");
            before_station2_code = cursor.getString(cursorIndex);
            cursor.moveToFirst();
            cursorIndex = cursor.getColumnIndex("before_station2");
            before_station2 = cursor.getString(cursorIndex);
            cursor.moveToFirst();
            cursorIndex = cursor.getColumnIndex("after_station_code1");
            after_station1_code = cursor.getString(cursorIndex);
            cursor.moveToFirst();
            cursorIndex = cursor.getColumnIndex("after_station1");
            after_station1 = cursor.getString(cursorIndex);
            cursor.moveToFirst();
            cursorIndex = cursor.getColumnIndex("after_station_code2");
            after_station2_code = cursor.getString(cursorIndex);
            cursor.moveToFirst();
            cursorIndex = cursor.getColumnIndex("after_station2");
            after_station2 = cursor.getString(cursorIndex);
            cursor.moveToFirst();
            //cursorIndex = cursor.getColumnIndex("main_station");
            //main_station = cursor.getString(cursorIndex);
            //cursor.moveToFirst();
            cursorIndex = cursor.getColumnIndex("main_station_code");
            main_station_code = cursor.getString(cursorIndex);

            ProcessDB.closingCursor();
            ProcessDB.closingDBFile();

        }
        catch(Exception e){
            Log.e("subway_error",e.getMessage());
        }
    }


    public String makeQuery(String stationName, int lineNum){

        String lineNumTbl = null;
        String madeQueryStr = null;

        lineNumTbl = "line"+lineNum+"_station_info";

        madeQueryStr = "SELECT * FROM "+lineNumTbl+ " WHERE main_station = \""+stationName +"\"";

        return madeQueryStr;
    }

    public int makeQuerySubwaytime(String stationName, String nowTime, String Week, String direction, int lineNum){

        int cnt = 0;

        try {
            String destinationStationName = null;
            String arriveTime = null;
            String tmpMin;

            String subwayTime_Query = null;
            subwayTime_Query = "SELECT ArriveTime, DestinationStationName FROM StationTime WHERE ArriveTime > \""+nowTime+"\" AND StationName=\""+stationName+"\" AND LineNum = (SELECT DISTINCT sLineNumChar FROM station_info WHERE sLineNum = \""+lineNum+"\") AND Week= \""+Week+"\" AND Direction = \""+direction+"\" LIMIT 7";
            ProcessDB.openDBFile(getApplicationContext());
            Cursor cursor = ProcessDB.selectQuery(subwayTime_Query);

//SELECT sLineNumChar FROM station_info WHERE sLineNum = "10"
            cnt = cursor.getCount();
            cursor.moveToFirst();
            for(int i = 0; i<cnt; i++){
                //cursor.moveToFirst();
                int cursorIndex = cursor.getColumnIndex("ArriveTime");
                arriveTime = cursor.getString(cursorIndex);

                tmpMin = "3";
                //cursor.moveToFirst();
                cursorIndex = cursor.getColumnIndex("DestinationStationName");
                destinationStationName = cursor.getString(cursorIndex);

                if(direction == "1"){
                    leftTimeList[i][0] = arriveTime;
                    leftTimeList[i][1] = destinationStationName;

                } else if(direction == "2"){
                    rightTimeList[i][0] = arriveTime;
                    rightTimeList[i][1] = destinationStationName;
                }

                cursor.moveToNext();
            }

            ProcessDB.closingCursor();
            ProcessDB.closingDBFile();



        }catch(Exception e){
            Log.e("subway_error",e.getMessage());
        }
        return cnt;
    }

    public static String GetCurrentTime() {
        String time = "";
        Calendar cal = Calendar.getInstance();

        time = String.format("%02d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));

        return time;
    }

    public String chktime(String arriveTime, String nowTime){

        String[] array1,array2;

        array1 = arriveTime.split("\\:");
        array2 = nowTime.split("\\:");

        int tmpArriveTime = 0;
        int tmpNowTime = 0;
        int tmpHour = 0;
        int tmpMin = 0;
        int tmpSecond = 0;
        tmpArriveTime = Integer.parseInt(array1[0]) * 3600 + Integer.parseInt(array1[1]) * 60 + Integer.parseInt(array1[2]);
        tmpNowTime = Integer.parseInt(array2[0]) * 3600 + Integer.parseInt(array2[1]) * 60 + Integer.parseInt(array2[2]);

        int tmpResult = tmpArriveTime - tmpNowTime;

        //tmpHour = tmpResult / 3600;
        //tmpMin = (tmpResult % 3600) /60;
        //tmpSecond = (tmpResult % 3600) % 60;
        tmpMin = tmpResult / 60;
        /*Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();


        cal1.set(0, 0, 0, Integer.parseInt(array1[0]), Integer.parseInt(array1[1]), Integer.parseInt(array1[2]));
        cal2.set(0, 0, 0, Integer.parseInt(array2[0]), Integer.parseInt(array2[1]), Integer.parseInt(array2[2]));

        cal3.setTimeInMillis(cal2.getTimeInMillis() - cal1.getTimeInMillis());

        return cal3.get(Calendar.MINUTE) + cal3.get(Calendar.SECOND) + "";*/
        return tmpMin+"";
    }

    public void update_db(String stationID, String stationName, int index){

        ProcessDB.openDBFile(getApplicationContext());
        ProcessDB.updateDBFile(index, stationID, stationName,1);
        ProcessDB.closingCursor();
        ProcessDB.closingDBFile();

    }

}

class SubwayTime {
    String direction_left;
    String remaintime_left;
    String arrivetime_left;
    String direction_right;
    String remaintime_right;
    String arrivetime_right;
    SubwayTime(String ldirection, String lremaintime, String larrivetime,String rdirection, String rremaintime, String rarrivetime){
        direction_left = ldirection;
        remaintime_left = lremaintime;
        arrivetime_left = larrivetime;
        direction_right = rdirection;
        remaintime_right = rremaintime;
        arrivetime_right = rarrivetime;
    }
}

class SubwayTimeAdapter extends BaseAdapter {

    Context con;
    LayoutInflater inflater;
    ArrayList<SubwayTime> arD;
    int layout;

    public SubwayTimeAdapter(Context context, int alayout, ArrayList<SubwayTime> aard) {
        con = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arD = aard;
        layout = alayout;
    }

    @Override
    public int getCount() {
        return arD.size();
    }

    @Override
    public Object getItem(int position){
        return arD.get(position).arrivetime_left;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = inflater.inflate(layout, parent,false);
        }


        TextView direction_left_txt = (TextView)convertView.findViewById(R.id.direction_left_txt);
        direction_left_txt.setText(arD.get(position).direction_left);

        TextView direction_right_txt = (TextView)convertView.findViewById(R.id.direction_right_txt);
        direction_right_txt.setText(arD.get(position).direction_right);

        TextView arrivetime_left_txt = (TextView)convertView.findViewById(R.id.arrivetime_left_txt);
        arrivetime_left_txt.setText(arD.get(position).arrivetime_left);

        TextView arrivetime_right_txt = (TextView)convertView.findViewById(R.id.arrivetime_right_txt);
        arrivetime_right_txt.setText(arD.get(position).arrivetime_right);

        TextView remaintime_left_txt = (TextView)convertView.findViewById(R.id.remaintime_left_txt);
        remaintime_left_txt.setText(arD.get(position).remaintime_left);

        TextView remaintime_right_txt = (TextView)convertView.findViewById(R.id.remaintime_right_txt);
        remaintime_right_txt.setText(arD.get(position).remaintime_right);
/*
        if(position == 0){
            direction_left_txt.setTextColor(Color.parseColor("#ff0000"));
        }
*/
        return convertView;

    }
}