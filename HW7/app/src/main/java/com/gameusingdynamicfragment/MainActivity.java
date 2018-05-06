package com.gameusingdynamicfragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 新增統計遊戲局數和輸贏的變數
    private int miCountSet = 0, miCountPlayerWin = 0, miCountComWin = 0, miCountDraw = 0;

    private Button mBtnRollingDice, mBtnShowResult;
    private ImageView mImgDice;

    private Bundle bundle = new Bundle();

    private int[] diceImg = new int[]{
            R.drawable.dice01, R.drawable.dice02, R.drawable.dice03,
            R.drawable.dice04, R.drawable.dice05, R.drawable.dice06};

    private boolean isDiceRoll = false;
    private Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnRollingDice = (Button) findViewById(R.id.btnRollingDice);
        mBtnShowResult = (Button) findViewById(R.id.btnShowRst);
        mImgDice = (ImageView) findViewById(R.id.imgDice);
        intent.setClass(MainActivity.this, GameStatistics.class);

        mBtnRollingDice.setOnClickListener(btnOnClick);
        mBtnShowResult.setOnClickListener(btnOnClick);
    }
    public void sendMessage() {
        bundle.putInt("Draw'", miCountDraw);
        bundle.putInt("Set", miCountSet);
        bundle.putInt("PlayerWin", miCountPlayerWin);
        bundle.putInt("ComWin", miCountComWin);
        intent.putExtras(bundle);

        startActivity(intent);

    }

    private void rollingDice() {
        if (isDiceRoll)
            return;
        isDiceRoll = true;

        Resources res = getResources();
        final AnimationDrawable animDraw = (AnimationDrawable) res.getDrawable(R.drawable.anim_roll_dice);
        mImgDice.setImageDrawable(animDraw);
        animDraw.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animDraw.stop();
                rollingAndRst();
                isDiceRoll = false;
            }
        }, 1500);
    }

    public void rollingAndRst() {
        int diceNum = (int) (Math.random() * 6);
        String rst;
        miCountSet++;

        if (diceNum <= 1) {
            rst = getString(R.string.player_win);
            Toast.makeText(this, rst, Toast.LENGTH_LONG).show();
            miCountPlayerWin++;
        }
        else if (diceNum <= 3) {
            rst = getString(R.string.player_draw);
            Toast.makeText(this, rst, Toast.LENGTH_LONG).show();
            miCountDraw++;
            Log.v("drawCount", String.valueOf(miCountDraw));
        }
        else {
            rst = getString(R.string.player_lose);
            Toast.makeText(this, rst, Toast.LENGTH_LONG).show();
            miCountComWin++;
        }

        mImgDice.setImageDrawable(getResources().getDrawable(diceImg[diceNum]));
    }

    private View.OnClickListener btnOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnRollingDice:
                    rollingDice();
                    break;
                case R.id.btnShowRst:
                    sendMessage();
                    break;
            }
        }
    };
}
