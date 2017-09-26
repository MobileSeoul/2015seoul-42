package com.nodedata.project.subway;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by yeoinhyo on 15. 10. 23..
 */
public class StartActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(StartActivity.this, MainActivity.class));

                overridePendingTransition(R.anim.fade, R.anim.hold);
                finish();
            }

        }, 1000);
    }
}
