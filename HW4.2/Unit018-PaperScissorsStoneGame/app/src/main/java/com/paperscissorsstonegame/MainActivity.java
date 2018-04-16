package com.paperscissorsstonegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtComPlay, mTxtResult;
    private Button mBtnScissors, mBtnStone, mBtnPaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtComPlay = (TextView)findViewById(R.id.txtComPlay);
        mTxtResult = (TextView)findViewById(R.id.txtResult);
        mBtnScissors = (Button)findViewById(R.id.btnScissors);
        mBtnStone = (Button)findViewById(R.id.btnStone);
        mBtnPaper = (Button)findViewById(R.id.btnPaper);

        mBtnScissors.setOnClickListener(btnScissorsOnClick);
        mBtnStone.setOnClickListener(btnStoneOnClick);
        mBtnPaper.setOnClickListener(btnPaperOnClick);
    }

    private View.OnClickListener btnScissorsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            // 決定電腦出拳.
            int iComPlay = (int)(Math.random()*3 + 1);
            output(1, iComPlay);
        }
    };

    private View.OnClickListener btnStoneOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            // 決定電腦出拳.
            int iComPlay = (int)(Math.random()*3 + 1);
            output(2, iComPlay);
        }
    };

    private View.OnClickListener btnPaperOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            // 決定電腦出拳.
            int iComPlay = (int)(Math.random()*3 + 1);
            output(3, iComPlay);
        }
    };

    private void output(int player, int com){
        int rst = new Handler().foo(player, com);
        // 1 – 剪刀, 2 – 石頭, 3 – 布.
        if (com == 1)
            mTxtComPlay.setText(R.string.play_scissors);
        else if (com == 2)
            mTxtComPlay.setText(R.string.play_stone);
        else
            mTxtComPlay.setText(R.string.play_paper);

        if (rst == 3)
            mTxtResult.setText(getString(R.string.result) + getString(R.string.player_draw));
        else if (rst == 2)
            mTxtResult.setText(getString(R.string.result) + getString(R.string.player_lose));
        else
            mTxtResult.setText(getString(R.string.result) + getString(R.string.player_win));
    }
}
