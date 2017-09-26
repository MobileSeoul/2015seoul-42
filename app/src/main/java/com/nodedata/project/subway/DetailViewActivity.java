package com.nodedata.project.subway;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by apple on 15. 10. 25..
 */
public class DetailViewActivity extends Activity{

    static String[] mStringStationCode;

    static DBSelect pdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        pdb = new DBSelect();
        pdb.openDBFile(DetailViewActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailview);

        String stringStationCode = intent.getStringExtra("strArrayStationCode");

        mStringStationCode = stringStationCode.split(",");
        String[] strArrayStationName = new String[mStringStationCode.length];
        String[] strArrayStationLine = new String[mStringStationCode.length];

        for (int i = 0; i < mStringStationCode.length; i++)
        {
            strArrayStationName[i] = pdb.getDBStationCodetoStationName(mStringStationCode[i]);
            strArrayStationLine[i] = getStationName(pdb.getDBStationCodetoStationLine(mStringStationCode[i]));
        }

        LinearLayout linearLayout;
        linearLayout = (LinearLayout) findViewById(R.id.linearLayoutDetailView);

        for (int i = 0; i < mStringStationCode.length; i++) {
            TextView textView;
            textView = new TextView(this);
            textView.setText(strArrayStationLine[i] + " - " + strArrayStationName[i]);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(25);

            linearLayout.addView(textView);

            if (i == 2 || i == 6 || i == 10 || i == 14 || i == 18 || i == 22)
            {
                TextView textView2 = new TextView(this);
                textView2.setText("<환승 경로 보기>");
                textView2.setTag(i);
                textView2.setGravity(Gravity.CENTER);
                textView2.setTextSize(40);
                textView2.setTextColor(Color.RED);

                LinearLayout linearLayoutOnclick = (LinearLayout) findViewById(R.id.linearLayoutDetailView);
                linearLayoutOnclick.addView(textView2);

                textView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("View ID", "View ID : " + v.getTag());
                        int intTag = Integer.valueOf(String.valueOf(v.getTag()));

                        Intent intentSubActivity = new Intent(DetailViewActivity.this, TransferRouteActivity.class);
                        String strImage = pdb.getDBRouteImage(mStringStationCode[intTag - 1], mStringStationCode[intTag], mStringStationCode[intTag + 2]);
                        intentSubActivity.putExtra("strImage", strImage);
                        startActivity(intentSubActivity);
                    }
                });

            }
        }

        Button close_button = (Button) findViewById(R.id.buttonMainBack);
        close_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    private String getStationName(String strArrayStation)
    {
        switch (strArrayStation) {
            case "1":
                strArrayStation = "1호선";
                break;
            case "2":
                strArrayStation = "2호선";
                break;
            case "3":
                strArrayStation = "3호선";
                break;
            case "4":
                strArrayStation = "4호선";
                break;
            case "5":
                strArrayStation = "5호선";
                break;
            case "6":
                strArrayStation = "6호선";
                break;
            case "7":
                strArrayStation = "7호선";
                break;
            case "8":
                strArrayStation = "8호선";
                break;
            case "9":
                strArrayStation = "9호선";
                break;
            case "B":
                strArrayStation = "분당선";
                break;
            case "S":
                strArrayStation = "신분";
                break;
            case "A":
                strArrayStation = "공항철도";
                break;
            case "E":
                strArrayStation = "에버라인";
                break;
            case "G":
                strArrayStation = "경춘선";
                break;
            case "I":
                strArrayStation = "인천1호선";
                break;
            case "K":
                strArrayStation = "경의중앙";
                break;
            case "SU":
                strArrayStation = "수인선";
                break;
            case "U":
                strArrayStation = "의정부선";
                break;
        }
        return strArrayStation;
    }
}
