package com.nodedata.project.subway;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by apple on 15. 10. 26..
 */
public class TransferRouteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transferroute);

        String strImage = intent.getStringExtra("strImage");

        DBSelect dbs = new DBSelect();
        dbs.openDBFile(this);
        String strTransferRouteText = "";
        strTransferRouteText = dbs.getDBRouteText(strImage);

        String strTransferRouteTextTemp = strTransferRouteText.replace("|", "\n");

        int intImageID = getImageID(strImage);

        if (intImageID != 0) {
            ImageView imageView = (ImageView) findViewById(R.id.imageTransferRoute);
            imageView.setImageResource(intImageID);
        }
        else {
            ImageView imageView = (ImageView) findViewById(R.id.imageTransferRoute);
            imageView.setMaxHeight(0);
        }

        TextView tv = (TextView) findViewById(R.id.textTransferRoute);
        tv.setText(strTransferRouteTextTemp);

        Button close_button = (Button) findViewById(R.id.buttonDetailBack);
        close_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    private int getImageID(String strImageCode)
    {
        int intResult = 0;
        switch (strImageCode)
        {
            case "boolgwang01":
                intResult = R.drawable.boolgwang01;
                break;
            case "boolgwang02":
                intResult = R.drawable.boolgwang02;
                break;
            case "boolgwang03":
                intResult = R.drawable.boolgwang03;
                break;
            case "boolgwang04":
                intResult = R.drawable.boolgwang04;
                break;
            case "changdong01":
                intResult = R.drawable.changdong01;
                break;
            case "changdong02":
                intResult = R.drawable.changdong02;
                break;
            case "changdong03":
                intResult = R.drawable.changdong03;
                break;
            case "changdong04":
                intResult = R.drawable.changdong04;
                break;
            case "changdong05":
                intResult = R.drawable.changdong05;
                break;
            case "changdong06":
                intResult = R.drawable.changdong06;
                break;
            case "changdong07":
                intResult = R.drawable.changdong07;
                break;
            case "changdong08":
                intResult = R.drawable.changdong08;
                break;
            case "choongmooro01":
                intResult = R.drawable.choongmooro01;
                break;
            case "choongmooro02":
                intResult = R.drawable.choongmooro02;
                break;
            case "choongmooro03":
                intResult = R.drawable.choongmooro03;
                break;
            case "choongmooro05":
                intResult = R.drawable.choongmooro05;
                break;
            case "choongmooro06":
                intResult = R.drawable.choongmooro06;
                break;
            case "choongmooro07":
                intResult = R.drawable.choongmooro07;
                break;
            case "choongmooro08":
                intResult = R.drawable.choongmooro08;
                break;
            case "chunggoo01":
                intResult = R.drawable.chunggoo01;
                break;
            case "chunggoo02":
                intResult = R.drawable.chunggoo02;
                break;
            case "chunggoo03":
                intResult = R.drawable.chunggoo03;
                break;
            case "chunggoo04":
                intResult = R.drawable.chunggoo04;
                break;
            case "chunggoo05":
                intResult = R.drawable.chunggoo05;
                break;
            case "chunggoo06":
                intResult = R.drawable.chunggoo06;
                break;
            case "chunggoo07":
                intResult = R.drawable.chunggoo07;
                break;
            case "chunggoo08":
                intResult = R.drawable.chunggoo08;
                break;
            case "chunho01":
                intResult = R.drawable.chunho01;
                break;
            case "chunho02":
                intResult = R.drawable.chunho02;
                break;
            case "chunho03":
                intResult = R.drawable.chunho03;
                break;
            case "chunho04":
                intResult = R.drawable.chunho04;
                break;
            case "chunho05":
                intResult = R.drawable.chunho05;
                break;
            case "chunho06":
                intResult = R.drawable.chunho06;
                break;
            case "chunho07":
                intResult = R.drawable.chunho07;
                break;
            case "chunho08":
                intResult = R.drawable.chunho08;
                break;
            case "daelim01":
                intResult = R.drawable.daelim01;
                break;
            case "daelim02":
                intResult = R.drawable.daelim02;
                break;
            case "daelim03":
                intResult = R.drawable.daelim03;
                break;
            case "daelim04":
                intResult = R.drawable.daelim04;
                break;
            case "daelim05":
                intResult = R.drawable.daelim05;
                break;
            case "daelim06":
                intResult = R.drawable.daelim06;
                break;
            case "daelim07":
                intResult = R.drawable.daelim07;
                break;
            case "daelim08":
                intResult = R.drawable.daelim08;
                break;
            case "dangsan01":
                intResult = R.drawable.dangsan01;
                break;
            case "dangsan02":
                intResult = R.drawable.dangsan02;
                break;
            case "dangsan03":
                intResult = R.drawable.dangsan03;
                break;
            case "dangsan04":
                intResult = R.drawable.dangsan04;
                break;
            case "dangsan05":
                intResult = R.drawable.dangsan05;
                break;
            case "dangsan06":
                intResult = R.drawable.dangsan06;
                break;
            case "dangsan07":
                intResult = R.drawable.dangsan07;
                break;
            case "dangsan08":
                intResult = R.drawable.dangsan08;
                break;
            case "dijitalmediasity11":
                intResult = R.drawable.dijitalmediasity11;
                break;
            case "dijitalmediasity12":
                intResult = R.drawable.dijitalmediasity12;
                break;
            case "dijitalmediasity15":
                intResult = R.drawable.dijitalmediasity15;
                break;
            case "dijitalmediasity16":
                intResult = R.drawable.dijitalmediasity16;
                break;
            case "dijitalmediasity19":
                intResult = R.drawable.dijitalmediasity19;
                break;
            case "dijitalmediasity20":
                intResult = R.drawable.dijitalmediasity20;
                break;
            case "dijitalmediasity23":
                intResult = R.drawable.dijitalmediasity23;
                break;
            case "dijitalmediasity24":
                intResult = R.drawable.dijitalmediasity24;
                break;
            case "dobongsan01":
                intResult = R.drawable.dobongsan01;
                break;
            case "dobongsan02":
                intResult = R.drawable.dobongsan02;
                break;
            case "dobongsan03":
                intResult = R.drawable.dobongsan03;
                break;
            case "dobongsan04":
                intResult = R.drawable.dobongsan04;
                break;
            case "dobongsan05":
                intResult = R.drawable.dobongsan05;
                break;
            case "dobongsan06":
                intResult = R.drawable.dobongsan06;
                break;
            case "dobongsan07":
                intResult = R.drawable.dobongsan07;
                break;
            case "dobongsan08":
                intResult = R.drawable.dobongsan08;
                break;
            case "dogog01":
                intResult = R.drawable.dogog01;
                break;
            case "dogog02":
                intResult = R.drawable.dogog02;
                break;
            case "dogog03":
                intResult = R.drawable.dogog03;
                break;
            case "dogog04":
                intResult = R.drawable.dogog04;
                break;
            case "dogog05":
                intResult = R.drawable.dogog05;
                break;
            case "dogog06":
                intResult = R.drawable.dogog06;
                break;
            case "dogog07":
                intResult = R.drawable.dogog07;
                break;
            case "dogog08":
                intResult = R.drawable.dogog08;
                break;
            case "dongdaemoon01":
                intResult = R.drawable.dongdaemoon01;
                break;
            case "dongdaemoon02":
                intResult = R.drawable.dongdaemoon02;
                break;
            case "dongdaemoon03":
                intResult = R.drawable.dongdaemoon03;
                break;
            case "dongdaemoon04":
                intResult = R.drawable.dongdaemoon04;
                break;
            case "dongdaemoon05":
                intResult = R.drawable.dongdaemoon05;
                break;
            case "dongdaemoon06":
                intResult = R.drawable.dongdaemoon06;
                break;
            case "dongdaemoon07":
                intResult = R.drawable.dongdaemoon07;
                break;
            case "dongdaemoon08":
                intResult = R.drawable.dongdaemoon08;
                break;
            case "dongdaemoonyeogsa01":
                intResult = R.drawable.dongdaemoonyeogsa01;
                break;
            case "dongdaemoonyeogsa02":
                intResult = R.drawable.dongdaemoonyeogsa02;
                break;
            case "dongdaemoonyeogsa03":
                intResult = R.drawable.dongdaemoonyeogsa03;
                break;
            case "dongdaemoonyeogsa04":
                intResult = R.drawable.dongdaemoonyeogsa04;
                break;
            case "dongdaemoonyeogsa05":
                intResult = R.drawable.dongdaemoonyeogsa05;
                break;
            case "dongdaemoonyeogsa06":
                intResult = R.drawable.dongdaemoonyeogsa06;
                break;
            case "dongdaemoonyeogsa07":
                intResult = R.drawable.dongdaemoonyeogsa07;
                break;
            case "dongdaemoonyeogsa08":
                intResult = R.drawable.dongdaemoonyeogsa08;
                break;
            case "dongdaemoonyeogsa09":
                intResult = R.drawable.dongdaemoonyeogsa09;
                break;
            case "dongdaemoonyeogsa10":
                intResult = R.drawable.dongdaemoonyeogsa10;
                break;
            case "dongdaemoonyeogsa11":
                intResult = R.drawable.dongdaemoonyeogsa11;
                break;
            case "dongdaemoonyeogsa12":
                intResult = R.drawable.dongdaemoonyeogsa12;
                break;
            case "dongdaemoonyeogsa13":
                intResult = R.drawable.dongdaemoonyeogsa13;
                break;
            case "dongdaemoonyeogsa14":
                intResult = R.drawable.dongdaemoonyeogsa14;
                break;
            case "dongdaemoonyeogsa15":
                intResult = R.drawable.dongdaemoonyeogsa15;
                break;
            case "dongdaemoonyeogsa16":
                intResult = R.drawable.dongdaemoonyeogsa16;
                break;
            case "dongdaemoonyeogsa17":
                intResult = R.drawable.dongdaemoonyeogsa17;
                break;
            case "dongdaemoonyeogsa18":
                intResult = R.drawable.dongdaemoonyeogsa18;
                break;
            case "dongdaemoonyeogsa19":
                intResult = R.drawable.dongdaemoonyeogsa19;
                break;
            case "dongdaemoonyeogsa20":
                intResult = R.drawable.dongdaemoonyeogsa20;
                break;
            case "dongdaemoonyeogsa21":
                intResult = R.drawable.dongdaemoonyeogsa21;
                break;
            case "dongdaemoonyeogsa22":
                intResult = R.drawable.dongdaemoonyeogsa22;
                break;
            case "dongdaemoonyeogsa23":
                intResult = R.drawable.dongdaemoonyeogsa23;
                break;
            case "dongdaemoonyeogsa24":
                intResult = R.drawable.dongdaemoonyeogsa24;
                break;
            case "dongjag01":
                intResult = R.drawable.dongjag01;
                break;
            case "dongjag02":
                intResult = R.drawable.dongjag02;
                break;
            case "dongjag03":
                intResult = R.drawable.dongjag03;
                break;
            case "dongjag04":
                intResult = R.drawable.dongjag04;
                break;
            case "dongjag05":
                intResult = R.drawable.dongjag05;
                break;
            case "dongjag06":
                intResult = R.drawable.dongjag06;
                break;
            case "dongjag07":
                intResult = R.drawable.dongjag07;
                break;
            case "dongjag08":
                intResult = R.drawable.dongjag08;
                break;
            case "dongmyoap01":
                intResult = R.drawable.dongmyoap01;
                break;
            case "dongmyoap02":
                intResult = R.drawable.dongmyoap02;
                break;
            case "dongmyoap03":
                intResult = R.drawable.dongmyoap03;
                break;
            case "dongmyoap04":
                intResult = R.drawable.dongmyoap04;
                break;
            case "dongmyoap05":
                intResult = R.drawable.dongmyoap05;
                break;
            case "dongmyoap06":
                intResult = R.drawable.dongmyoap06;
                break;
            case "dongmyoap07":
                intResult = R.drawable.dongmyoap07;
                break;
            case "dongmyoap08":
                intResult = R.drawable.dongmyoap08;
                break;
            case "gangnam01":
                intResult = R.drawable.gangnam01;
                break;
            case "gangnam02":
                intResult = R.drawable.gangnam02;
                break;
            case "gangnam03":
                intResult = R.drawable.gangnam03;
                break;
            case "gangnam04":
                intResult = R.drawable.gangnam04;
                break;
            case "garagsijang01":
                intResult = R.drawable.garagsijang01;
                break;
            case "garagsijang02":
                intResult = R.drawable.garagsijang02;
                break;
            case "garagsijang03":
                intResult = R.drawable.garagsijang03;
                break;
            case "garagsijang04":
                intResult = R.drawable.garagsijang04;
                break;
            case "garagsijang05":
                intResult = R.drawable.garagsijang05;
                break;
            case "garagsijang06":
                intResult = R.drawable.garagsijang06;
                break;
            case "garagsijang07":
                intResult = R.drawable.garagsijang07;
                break;
            case "garagsijang08":
                intResult = R.drawable.garagsijang08;
                break;
            case "gasandigital01":
                intResult = R.drawable.gasandigital01;
                break;
            case "gasandigital02":
                intResult = R.drawable.gasandigital02;
                break;
            case "gasandigital03":
                intResult = R.drawable.gasandigital03;
                break;
            case "gasandigital04":
                intResult = R.drawable.gasandigital04;
                break;
            case "gasandigital05":
                intResult = R.drawable.gasandigital05;
                break;
            case "gasandigital06":
                intResult = R.drawable.gasandigital06;
                break;
            case "gasandigital07":
                intResult = R.drawable.gasandigital07;
                break;
            case "gasandigital08":
                intResult = R.drawable.gasandigital08;
                break;
            case "gongdeog29":
                intResult = R.drawable.gongdeog29;
                break;
            case "gongdeog30":
                intResult = R.drawable.gongdeog30;
                break;
            case "gongdeog35":
                intResult = R.drawable.gongdeog35;
                break;
            case "gongdeog36":
                intResult = R.drawable.gongdeog36;
                break;
            case "gongdeog41":
                intResult = R.drawable.gongdeog41;
                break;
            case "gongdeog42":
                intResult = R.drawable.gongdeog42;
                break;
            case "gongdeog47":
                intResult = R.drawable.gongdeog47;
                break;
            case "gongdeog48":
                intResult = R.drawable.gongdeog48;
                break;
            case "goonja01":
                intResult = R.drawable.goonja01;
                break;
            case "goonja02":
                intResult = R.drawable.goonja02;
                break;
            case "goonja03":
                intResult = R.drawable.goonja03;
                break;
            case "goonja04":
                intResult = R.drawable.goonja04;
                break;
            case "goonja05":
                intResult = R.drawable.goonja05;
                break;
            case "goonja06":
                intResult = R.drawable.goonja06;
                break;
            case "goonja07":
                intResult = R.drawable.goonja07;
                break;
            case "goonja08":
                intResult = R.drawable.goonja08;
                break;
            case "gosogteominal01":
                intResult = R.drawable.gosogteominal01;
                break;
            case "gosogteominal02":
                intResult = R.drawable.gosogteominal02;
                break;
            case "gosogteominal03":
                intResult = R.drawable.gosogteominal03;
                break;
            case "gosogteominal04":
                intResult = R.drawable.gosogteominal04;
                break;
            case "gosogteominal05":
                intResult = R.drawable.gosogteominal05;
                break;
            case "gosogteominal06":
                intResult = R.drawable.gosogteominal06;
                break;
            case "gosogteominal07":
                intResult = R.drawable.gosogteominal07;
                break;
            case "gosogteominal08":
                intResult = R.drawable.gosogteominal08;
                break;
            case "gosogteominal09":
                intResult = R.drawable.gosogteominal09;
                break;
            case "gosogteominal10":
                intResult = R.drawable.gosogteominal10;
                break;
            case "gosogteominal11":
                intResult = R.drawable.gosogteominal11;
                break;
            case "gosogteominal12":
                intResult = R.drawable.gosogteominal12;
                break;
            case "gosogteominal13":
                intResult = R.drawable.gosogteominal13;
                break;
            case "gosogteominal14":
                intResult = R.drawable.gosogteominal14;
                break;
            case "gosogteominal15":
                intResult = R.drawable.gosogteominal15;
                break;
            case "gosogteominal16":
                intResult = R.drawable.gosogteominal16;
                break;
            case "gosogteominal17":
                intResult = R.drawable.gosogteominal17;
                break;
            case "gosogteominal18":
                intResult = R.drawable.gosogteominal18;
                break;
            case "gosogteominal19":
                intResult = R.drawable.gosogteominal19;
                break;
            case "gosogteominal20":
                intResult = R.drawable.gosogteominal20;
                break;
            case "gosogteominal21":
                intResult = R.drawable.gosogteominal21;
                break;
            case "gosogteominal22":
                intResult = R.drawable.gosogteominal22;
                break;
            case "gosogteominal23":
                intResult = R.drawable.gosogteominal23;
                break;
            case "gosogteominal24":
                intResult = R.drawable.gosogteominal24;
                break;
            case "gyeyang01":
                intResult = R.drawable.gyeyang01;
                break;
            case "gyeyang02":
                intResult = R.drawable.gyeyang02;
                break;
            case "gyeyang03":
                intResult = R.drawable.gyeyang03;
                break;
            case "gyeyang04":
                intResult = R.drawable.gyeyang04;
                break;
            case "gyodae01":
                intResult = R.drawable.gyodae01;
                break;
            case "gyodae02":
                intResult = R.drawable.gyodae02;
                break;
            case "gyodae03":
                intResult = R.drawable.gyodae03;
                break;
            case "gyodae04":
                intResult = R.drawable.gyodae04;
                break;
            case "gyodae05":
                intResult = R.drawable.gyodae05;
                break;
            case "gyodae06":
                intResult = R.drawable.gyodae06;
                break;
            case "gyodae07":
                intResult = R.drawable.gyodae07;
                break;
            case "gyodae08":
                intResult = R.drawable.gyodae08;
                break;
            case "habjeong01":
                intResult = R.drawable.habjeong01;
                break;
            case "habjeong02":
                intResult = R.drawable.habjeong02;
                break;
            case "habjeong03":
                intResult = R.drawable.habjeong03;
                break;
            case "habjeong04":
                intResult = R.drawable.habjeong04;
                break;
            case "habjeong05":
                intResult = R.drawable.habjeong05;
                break;
            case "habjeong06":
                intResult = R.drawable.habjeong06;
                break;
            case "habjeong07":
                intResult = R.drawable.habjeong07;
                break;
            case "ichon01":
                intResult = R.drawable.ichon01;
                break;
            case "ichon02":
                intResult = R.drawable.ichon02;
                break;
            case "ichon03":
                intResult = R.drawable.ichon03;
                break;
            case "ichon04":
                intResult = R.drawable.ichon04;
                break;
            case "ichon05":
                intResult = R.drawable.ichon05;
                break;
            case "ichon06":
                intResult = R.drawable.ichon06;
                break;
            case "ichon07":
                intResult = R.drawable.ichon07;
                break;
            case "ichon08":
                intResult = R.drawable.ichon08;
                break;
            case "isoo01":
                intResult = R.drawable.isoo01;
                break;
            case "isoo02":
                intResult = R.drawable.isoo02;
                break;
            case "isoo03":
                intResult = R.drawable.isoo03;
                break;
            case "isoo04":
                intResult = R.drawable.isoo04;
                break;
            case "isoo05":
                intResult = R.drawable.isoo05;
                break;
            case "isoo06":
                intResult = R.drawable.isoo06;
                break;
            case "isoo07":
                intResult = R.drawable.isoo07;
                break;
            case "isoo08":
                intResult = R.drawable.isoo08;
                break;
            case "jamsil01":
                intResult = R.drawable.jamsil01;
                break;
            case "jamsil02":
                intResult = R.drawable.jamsil02;
                break;
            case "jamsil03":
                intResult = R.drawable.jamsil03;
                break;
            case "jamsil04":
                intResult = R.drawable.jamsil04;
                break;
            case "jamsil05":
                intResult = R.drawable.jamsil05;
                break;
            case "jamsil06":
                intResult = R.drawable.jamsil06;
                break;
            case "jamsil07":
                intResult = R.drawable.jamsil07;
                break;
            case "jamsil08":
                intResult = R.drawable.jamsil08;
                break;
            case "jeongja01":
                intResult = R.drawable.jeongja01;
                break;
            case "jeongja02":
                intResult = R.drawable.jeongja02;
                break;
            case "jeongja03":
                intResult = R.drawable.jeongja03;
                break;
            case "jeongja04":
                intResult = R.drawable.jeongja04;
                break;
            case "jongro3ga01":
                intResult = R.drawable.jongro3ga01;
                break;
            case "jongro3ga02":
                intResult = R.drawable.jongro3ga02;
                break;
            case "jongro3ga03":
                intResult = R.drawable.jongro3ga03;
                break;
            case "jongro3ga04":
                intResult = R.drawable.jongro3ga04;
                break;
            case "jongro3ga05":
                intResult = R.drawable.jongro3ga05;
                break;
            case "jongro3ga06":
                intResult = R.drawable.jongro3ga06;
                break;
            case "jongro3ga07":
                intResult = R.drawable.jongro3ga07;
                break;
            case "jongro3ga08":
                intResult = R.drawable.jongro3ga08;
                break;
            case "jongro3ga09":
                intResult = R.drawable.jongro3ga09;
                break;
            case "jongro3ga10":
                intResult = R.drawable.jongro3ga10;
                break;
            case "jongro3ga11":
                intResult = R.drawable.jongro3ga11;
                break;
            case "jongro3ga12":
                intResult = R.drawable.jongro3ga12;
                break;
            case "jongro3ga13":
                intResult = R.drawable.jongro3ga13;
                break;
            case "jongro3ga14":
                intResult = R.drawable.jongro3ga14;
                break;
            case "jongro3ga15":
                intResult = R.drawable.jongro3ga15;
                break;
            case "jongro3ga16":
                intResult = R.drawable.jongro3ga16;
                break;
            case "jongro3ga17":
                intResult = R.drawable.jongro3ga17;
                break;
            case "jongro3ga18":
                intResult = R.drawable.jongro3ga18;
                break;
            case "jongro3ga19":
                intResult = R.drawable.jongro3ga19;
                break;
            case "jongro3ga20":
                intResult = R.drawable.jongro3ga20;
                break;
            case "jongro3ga21":
                intResult = R.drawable.jongro3ga21;
                break;
            case "jongro3ga22":
                intResult = R.drawable.jongro3ga22;
                break;
            case "jongro3ga23":
                intResult = R.drawable.jongro3ga23;
                break;
            case "jongro3ga24":
                intResult = R.drawable.jongro3ga24;
                break;
            case "kkachisan01":
                intResult = R.drawable.kkachisan01;
                break;
            case "kkachisan02":
                intResult = R.drawable.kkachisan02;
                break;
            case "kkachisan03":
                intResult = R.drawable.kkachisan03;
                break;
            case "kkachisan04":
                intResult = R.drawable.kkachisan04;
                break;
            case "moran01":
                intResult = R.drawable.moran01;
                break;
            case "moran02":
                intResult = R.drawable.moran02;
                break;
            case "moran03":
                intResult = R.drawable.moran03;
                break;
            case "moran04":
                intResult = R.drawable.moran04;
                break;
            case "nowon01":
                intResult = R.drawable.nowon01;
                break;
            case "nowon02":
                intResult = R.drawable.nowon02;
                break;
            case "nowon03":
                intResult = R.drawable.nowon03;
                break;
            case "nowon04":
                intResult = R.drawable.nowon04;
                break;
            case "nowon05":
                intResult = R.drawable.nowon05;
                break;
            case "nowon06":
                intResult = R.drawable.nowon06;
                break;
            case "nowon07":
                intResult = R.drawable.nowon07;
                break;
            case "nowon08":
                intResult = R.drawable.nowon08;
                break;
            case "ogeum01":
                intResult = R.drawable.ogeum01;
                break;
            case "ogeum02":
                intResult = R.drawable.ogeum02;
                break;
            case "ogeum04":
                intResult = R.drawable.ogeum04;
                break;
            case "ogeum05":
                intResult = R.drawable.ogeum05;
                break;
            case "oksoo01":
                intResult = R.drawable.oksoo01;
                break;
            case "oksoo02":
                intResult = R.drawable.oksoo02;
                break;
            case "oksoo03":
                intResult = R.drawable.oksoo03;
                break;
            case "oksoo04":
                intResult = R.drawable.oksoo04;
                break;
            case "oksoo05":
                intResult = R.drawable.oksoo05;
                break;
            case "oksoo06":
                intResult = R.drawable.oksoo06;
                break;
            case "oksoo07":
                intResult = R.drawable.oksoo07;
                break;
            case "oksoo08":
                intResult = R.drawable.oksoo08;
                break;
            case "oljiro3ga01":
                intResult = R.drawable.oljiro3ga01;
                break;
            case "oljiro3ga02":
                intResult = R.drawable.oljiro3ga02;
                break;
            case "oljiro3ga03":
                intResult = R.drawable.oljiro3ga03;
                break;
            case "oljiro3ga04":
                intResult = R.drawable.oljiro3ga04;
                break;
            case "oljiro3ga05":
                intResult = R.drawable.oljiro3ga05;
                break;
            case "oljiro3ga06":
                intResult = R.drawable.oljiro3ga06;
                break;
            case "oljiro3ga07":
                intResult = R.drawable.oljiro3ga07;
                break;
            case "oljiro3ga08":
                intResult = R.drawable.oljiro3ga08;
                break;
            case "onsoo01":
                intResult = R.drawable.onsoo01;
                break;
            case "onsoo02":
                intResult = R.drawable.onsoo02;
                break;
            case "onsoo03":
                intResult = R.drawable.onsoo03;
                break;
            case "onsoo04":
                intResult = R.drawable.onsoo04;
                break;
            case "onsoo05":
                intResult = R.drawable.onsoo05;
                break;
            case "onsoo06":
                intResult = R.drawable.onsoo06;
                break;
            case "onsoo07":
                intResult = R.drawable.onsoo07;
                break;
            case "onsoo08":
                intResult = R.drawable.onsoo08;
                break;
            case "sadang01":
                intResult = R.drawable.sadang01;
                break;
            case "sadang02":
                intResult = R.drawable.sadang02;
                break;
            case "sadang03":
                intResult = R.drawable.sadang03;
                break;
            case "sadang04":
                intResult = R.drawable.sadang04;
                break;
            case "sadang05":
                intResult = R.drawable.sadang05;
                break;
            case "sadang06":
                intResult = R.drawable.sadang06;
                break;
            case "sadang07":
                intResult = R.drawable.sadang07;
                break;
            case "sadang08":
                intResult = R.drawable.sadang08;
                break;
            case "samgagji01":
                intResult = R.drawable.samgagji01;
                break;
            case "samgagji02":
                intResult = R.drawable.samgagji02;
                break;
            case "samgagji03":
                intResult = R.drawable.samgagji03;
                break;
            case "samgagji04":
                intResult = R.drawable.samgagji04;
                break;
            case "samgagji05":
                intResult = R.drawable.samgagji05;
                break;
            case "samgagji06":
                intResult = R.drawable.samgagji06;
                break;
            case "samgagji07":
                intResult = R.drawable.samgagji07;
                break;
            case "samgagji08":
                intResult = R.drawable.samgagji08;
                break;
            case "sangbong01":
                intResult = R.drawable.sangbong01;
                break;
            case "sangbong02":
                intResult = R.drawable.sangbong02;
                break;
            case "sangbong03":
                intResult = R.drawable.sangbong03;
                break;
            case "sangbong04":
                intResult = R.drawable.sangbong04;
                break;
            case "sangbong05":
                intResult = R.drawable.sangbong05;
                break;
            case "sangbong06":
                intResult = R.drawable.sangbong06;
                break;
            case "sangbong07":
                intResult = R.drawable.sangbong07;
                break;
            case "sangbong08":
                intResult = R.drawable.sangbong08;
                break;
            case "sangbong09":
                intResult = R.drawable.sangbong09;
                break;
            case "sangbong10":
                intResult = R.drawable.sangbong10;
                break;
            case "sangbong11":
                intResult = R.drawable.sangbong11;
                break;
            case "sangbong12":
                intResult = R.drawable.sangbong12;
                break;
            case "sangbong13":
                intResult = R.drawable.sangbong13;
                break;
            case "sangbong14":
                intResult = R.drawable.sangbong14;
                break;
            case "sangbong15":
                intResult = R.drawable.sangbong15;
                break;
            case "sangbong16":
                intResult = R.drawable.sangbong16;
                break;
            case "seokgye01":
                intResult = R.drawable.seokgye01;
                break;
            case "seokgye02":
                intResult = R.drawable.seokgye02;
                break;
            case "seokgye03":
                intResult = R.drawable.seokgye03;
                break;
            case "seokgye04":
                intResult = R.drawable.seokgye04;
                break;
            case "seokgye05":
                intResult = R.drawable.seokgye05;
                break;
            case "seokgye06":
                intResult = R.drawable.seokgye06;
                break;
            case "seokgye07":
                intResult = R.drawable.seokgye07;
                break;
            case "seokgye08":
                intResult = R.drawable.seokgye08;
                break;
            case "seonleung01":
                intResult = R.drawable.seonleung01;
                break;
            case "seonleung02":
                intResult = R.drawable.seonleung02;
                break;
            case "seonleung03":
                intResult = R.drawable.seonleung03;
                break;
            case "seonleung04":
                intResult = R.drawable.seonleung04;
                break;
            case "seonleung05":
                intResult = R.drawable.seonleung05;
                break;
            case "seonleung06":
                intResult = R.drawable.seonleung06;
                break;
            case "seonleung07":
                intResult = R.drawable.seonleung07;
                break;
            case "seonleung08":
                intResult = R.drawable.seonleung08;
                break;
            case "seoul13":
                intResult = R.drawable.seoul13;
                break;
            case "seoul14":
                intResult = R.drawable.seoul14;
                break;
            case "seoul17":
                intResult = R.drawable.seoul17;
                break;
            case "seoul18":
                intResult = R.drawable.seoul18;
                break;
            case "seoul21":
                intResult = R.drawable.seoul21;
                break;
            case "seoul22":
                intResult = R.drawable.seoul22;
                break;
            case "seoul25":
                intResult = R.drawable.seoul25;
                break;
            case "seoul26":
                intResult = R.drawable.seoul26;
                break;
            case "sichung01":
                intResult = R.drawable.sichung01;
                break;
            case "sichung02":
                intResult = R.drawable.sichung02;
                break;
            case "sichung03":
                intResult = R.drawable.sichung03;
                break;
            case "sichung04":
                intResult = R.drawable.sichung04;
                break;
            case "sichung05":
                intResult = R.drawable.sichung05;
                break;
            case "sichung06":
                intResult = R.drawable.sichung06;
                break;
            case "sichung07":
                intResult = R.drawable.sichung07;
                break;
            case "sichung08":
                intResult = R.drawable.sichung08;
                break;
            case "sindang01":
                intResult = R.drawable.sindang01;
                break;
            case "sindang02":
                intResult = R.drawable.sindang02;
                break;
            case "sindang03":
                intResult = R.drawable.sindang03;
                break;
            case "sindang04":
                intResult = R.drawable.sindang04;
                break;
            case "sindang05":
                intResult = R.drawable.sindang05;
                break;
            case "sindang06":
                intResult = R.drawable.sindang06;
                break;
            case "sindang07":
                intResult = R.drawable.sindang07;
                break;
            case "sindang08":
                intResult = R.drawable.sindang08;
                break;
            case "sindorim01":
                intResult = R.drawable.sindorim01;
                break;
            case "sindorim02":
                intResult = R.drawable.sindorim02;
                break;
            case "sindorim03":
                intResult = R.drawable.sindorim03;
                break;
            case "sindorim04":
                intResult = R.drawable.sindorim04;
                break;
            case "sindorim05":
                intResult = R.drawable.sindorim05;
                break;
            case "sindorim06":
                intResult = R.drawable.sindorim06;
                break;
            case "sindorim07":
                intResult = R.drawable.sindorim07;
                break;
            case "sindorim08":
                intResult = R.drawable.sindorim08;
                break;
            case "sindorim09":
                intResult = R.drawable.sindorim09;
                break;
            case "sindorim10":
                intResult = R.drawable.sindorim10;
                break;
            case "sindorim11":
                intResult = R.drawable.sindorim11;
                break;
            case "sindorim12":
                intResult = R.drawable.sindorim12;
                break;
            case "singil01":
                intResult = R.drawable.singil01;
                break;
            case "singil02":
                intResult = R.drawable.singil02;
                break;
            case "singil03":
                intResult = R.drawable.singil03;
                break;
            case "singil04":
                intResult = R.drawable.singil04;
                break;
            case "singil05":
                intResult = R.drawable.singil05;
                break;
            case "singil06":
                intResult = R.drawable.singil06;
                break;
            case "singil07":
                intResult = R.drawable.singil07;
                break;
            case "singil08":
                intResult = R.drawable.singil08;
                break;
            case "sooseo01":
                intResult = R.drawable.sooseo01;
                break;
            case "sooseo02":
                intResult = R.drawable.sooseo02;
                break;
            case "sooseo03":
                intResult = R.drawable.sooseo03;
                break;
            case "sooseo04":
                intResult = R.drawable.sooseo04;
                break;
            case "sooseo05":
                intResult = R.drawable.sooseo05;
                break;
            case "sooseo06":
                intResult = R.drawable.sooseo06;
                break;
            case "sooseo07":
                intResult = R.drawable.sooseo07;
                break;
            case "sooseo08":
                intResult = R.drawable.sooseo08;
                break;
            case "sungsoo01":
                intResult = R.drawable.sungsoo01;
                break;
            case "sungsoo02":
                intResult = R.drawable.sungsoo02;
                break;
            case "sungsoo03":
                intResult = R.drawable.sungsoo03;
                break;
            case "sungsoo04":
                intResult = R.drawable.sungsoo04;
                break;
            case "taeleungibgoo01":
                intResult = R.drawable.taeleungibgoo01;
                break;
            case "taeleungibgoo02":
                intResult = R.drawable.taeleungibgoo02;
                break;
            case "taeleungibgoo03":
                intResult = R.drawable.taeleungibgoo03;
                break;
            case "taeleungibgoo04":
                intResult = R.drawable.taeleungibgoo04;
                break;
            case "taeleungibgoo05":
                intResult = R.drawable.taeleungibgoo05;
                break;
            case "taeleungibgoo06":
                intResult = R.drawable.taeleungibgoo06;
                break;
            case "taeleungibgoo07":
                intResult = R.drawable.taeleungibgoo07;
                break;
            case "taeleungibgoo08":
                intResult = R.drawable.taeleungibgoo08;
                break;
            case "yagsoo01":
                intResult = R.drawable.yagsoo01;
                break;
            case "yagsoo02":
                intResult = R.drawable.yagsoo02;
                break;
            case "yagsoo03":
                intResult = R.drawable.yagsoo03;
                break;
            case "yagsoo04":
                intResult = R.drawable.yagsoo04;
                break;
            case "yagsoo05":
                intResult = R.drawable.yagsoo05;
                break;
            case "yagsoo06":
                intResult = R.drawable.yagsoo06;
                break;
            case "yagsoo07":
                intResult = R.drawable.yagsoo07;
                break;
            case "yagsoo08":
                intResult = R.drawable.yagsoo08;
                break;
            case "yangjae01":
                intResult = R.drawable.yangjae01;
                break;
            case "yangjae02":
                intResult = R.drawable.yangjae02;
                break;
            case "yangjae03":
                intResult = R.drawable.yangjae03;
                break;
            case "yangjae04":
                intResult = R.drawable.yangjae04;
                break;
            case "yangjae05":
                intResult = R.drawable.yangjae05;
                break;
            case "yangjae06":
                intResult = R.drawable.yangjae06;
                break;
            case "yangjae07":
                intResult = R.drawable.yangjae07;
                break;
            case "yangjae08":
                intResult = R.drawable.yangjae08;
                break;
            case "yeongdeungpo01":
                intResult = R.drawable.yeongdeungpo01;
                break;
            case "yeongdeungpo02":
                intResult = R.drawable.yeongdeungpo02;
                break;
            case "yeongdeungpo03":
                intResult = R.drawable.yeongdeungpo03;
                break;
            case "yeongdeungpo04":
                intResult = R.drawable.yeongdeungpo04;
                break;
            case "yeongdeungpo05":
                intResult = R.drawable.yeongdeungpo05;
                break;
            case "yeongdeungpo06":
                intResult = R.drawable.yeongdeungpo06;
                break;
            case "yeongdeungpo07":
                intResult = R.drawable.yeongdeungpo07;
                break;
            case "yeongdeungpo08":
                intResult = R.drawable.yeongdeungpo08;
                break;
            case "yeonsinsae01":
                intResult = R.drawable.yeonsinsae01;
                break;
            case "yeonsinsae02":
                intResult = R.drawable.yeonsinsae02;
                break;
            case "yeonsinsae03":
                intResult = R.drawable.yeonsinsae03;
                break;
            case "yeonsinsae04":
                intResult = R.drawable.yeonsinsae04;
                break;
        }

        return intResult;
    }

}
