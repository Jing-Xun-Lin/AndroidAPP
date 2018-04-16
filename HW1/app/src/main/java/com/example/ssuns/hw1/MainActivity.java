package com.example.ssuns.hw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity {

    private EditText mInputSex, mInputAge;
    private Button mConfirm;
    private TextView mTxtR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputSex = (EditText) findViewById(R.id.inputSex);
        mInputAge = (EditText) findViewById(R.id.inputAge);
        mConfirm = (Button) findViewById(R.id.confirm);
        mTxtR = (TextView) findViewById(R.id.txtR);

        mConfirm.setOnClickListener(btnOKOnClick);
    }

    private View.OnClickListener btnOKOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            String strSex = mInputSex.getText().toString();
            int iAge = Integer.parseInt(mInputAge.getText().toString());

            String strSug = getString(R.string.txtR);
            if (strSex.equals(getString(R.string.sex_male)))
                if (iAge < 30)
                    strSug += getString(R.string.sex_male);
                else if (iAge <= 35)
                    strSug += getString(R.string.sug_get_married);
                else
                    strSug += getString(R.string.sug_find_couple);
            else
                if (iAge < 28)
                    strSug += getString(R.string.sug_not_hurry);
                else if (iAge <= 32)
                    strSug += getString(R.string.sug_get_married);
                else
                    strSug += getString(R.string.sug_find_couple);

                mTxtR.setText(strSug);
        }
    };
}
