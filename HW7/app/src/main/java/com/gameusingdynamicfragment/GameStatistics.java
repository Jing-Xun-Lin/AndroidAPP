package com.gameusingdynamicfragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GameStatistics extends AppCompatActivity {

    private EditText edtCountSet,
            edtCountPlayerWin,
            edtCountComWin,
            edtCountDraw;

    private Button mBtnBack;


    private Intent intent = new Intent();
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_statistics);

        edtCountSet = (EditText) findViewById(R.id.edtCountSet);
        edtCountPlayerWin = (EditText) findViewById(R.id.edtCountPlayerWin);
        edtCountComWin = (EditText) findViewById(R.id.edtCountComWin);
        edtCountDraw = (EditText) findViewById(R.id.edtCountDraw);

        mBtnBack = (Button) findViewById(R.id.BtnBack);

        intent = getIntent();
        bundle = intent.getExtras();

        ShowRst();
        mBtnBack.setOnClickListener(btnOnClick);
    }

    private void ShowRst() {
        if (bundle != null) {
            int draw = bundle.getInt("Set") - bundle.getInt("PlayerWin") - bundle.getInt("ComWin");
            edtCountSet.setText(String.valueOf(bundle.getInt("Set")));
            edtCountPlayerWin.setText(String.valueOf(bundle.getInt("PlayerWin")));
            edtCountComWin.setText(String.valueOf(bundle.getInt("ComWin")));
            edtCountDraw.setText(String.valueOf(draw));
        } else {
            Toast.makeText(this, "請先擲骰子", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private View.OnClickListener btnOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //返回
            Log.v("countSet:", String.valueOf(bundle.getInt("countSet")));
            Log.v("countPlayerWin:", String.valueOf(bundle.getInt("countPlayerWin")));
            Log.v("countComWin", String.valueOf(bundle.getInt("countComWin")));
            Log.v("countDraw", String.valueOf(bundle.getInt("countDraw")));
            finish();
        }
    };

}
