package com.example.ssuns.hw8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner mItemSpn;
    private Button mInputBtn, mRecBtn;
    private TextView mDataOut;
    private EditText mCostEdt;
    private DatePicker mDatePicker;

    private int itemNum;
    private String date;
    private ArrayList<String> itemList = new ArrayList<String>();

    private Intent intent;
    private AdapterView.OnClickListener recBtnOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(MainActivity.this, recordActivity.class);
            intent.putStringArrayListExtra("itemList", itemList);
            startActivity(intent);
        }
    };
    private AdapterView.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String cost = mCostEdt.getText().toString();
            if (cost.matches("")) {
                Toast.makeText(MainActivity.this, "You didn't enter how much you cost!", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                String itemNumStr = String.valueOf(itemNum);
                String item = "項目" + itemNumStr;
                String oneItem = item;
                Toast.makeText(MainActivity.this, cost, Toast.LENGTH_SHORT).show();
                date = mDatePicker.getYear() + "/" + (mDatePicker.getMonth() + 1) + "/" + mDatePicker.getDayOfMonth();
                oneItem += "            " + date + "           " + mItemSpn.getSelectedItem().toString() + "           " + cost;
                itemList.add(oneItem);
                itemNum++;
            }
        }
    };
    private DatePicker.OnDateChangedListener onChanged = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String dataSelected = mDatePicker.getYear() + "/" + mDatePicker.getMonth() + "/" + mDatePicker.getDayOfMonth();
            mDataOut.setText(dataSelected);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        varInit();

        mDatePicker.setOnDateChangedListener(onChanged);
        mInputBtn.setOnClickListener(onClick);
        mRecBtn.setOnClickListener(recBtnOnClick);
    }

    private void varInit(){
        mItemSpn = (Spinner) findViewById(R.id.itemSpn);
        mInputBtn = (Button) findViewById(R.id.inputBtn);
        mRecBtn = (Button) findViewById(R.id.recordBtn);
        mDataOut = (TextView) findViewById(R.id.dataOut);
        mCostEdt = (EditText) findViewById(R.id.costEdt);
        mDatePicker = (DatePicker) findViewById(R.id.datePicker);
    }
}
