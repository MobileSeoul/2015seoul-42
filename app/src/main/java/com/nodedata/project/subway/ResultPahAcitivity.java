package com.nodedata.project.subway;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yeoinhyo on 15. 10. 30..
 */
public class ResultPahAcitivity extends Activity implements View.OnClickListener{

    Button normal_btn;
    Button wheelchair_btn;
    Button babycar_btn;
    Button detailpathinfo_btn;
    TextView pathinfo_txt;

    String startStationName;
    String endStationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultpath);

        startStationName = getIntent().getExtras().getString("startStation");
        endStationName = getIntent().getExtras().getString("endStation");



        normal_btn = (Button)findViewById(R.id.normal_btn);
        normal_btn.setOnClickListener(this);

        wheelchair_btn = (Button)findViewById(R.id.wheelchair_btn);
        wheelchair_btn.setOnClickListener(this);

        babycar_btn = (Button)findViewById(R.id.babycar_btn);
        babycar_btn.setOnClickListener(this);

        detailpathinfo_btn = (Button)findViewById(R.id.detailpathinfo_btn);
        detailpathinfo_btn.setOnClickListener(this);

        pathinfo_txt = (TextView)findViewById(R.id.pathinfo_txt);

        searchPath();
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.normal_btn:
                break;
            case R.id.wheelchair_btn:
                break;
            case R.id.babycar_btn:
                break;
            case R.id.detailpathinfo_btn:
                break;

        }
    }

    public void searchPath(){
        /*
        String searchURL = "http://swopenAPI.seoul.go.kr/api/subway/4150495f3231303379656f696e68796f/xml/shortestRoute/0/5/"+startStationName+"/"+endStationName+"/";
        URL url = new URL(searchURL);

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        if(conn != null){

        }
        */
    }

}
