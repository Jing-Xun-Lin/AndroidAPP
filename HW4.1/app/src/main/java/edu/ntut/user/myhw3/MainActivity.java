package edu.ntut.user.myhw3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RadioGroup mRadGrp;
    private Spinner mSpnAge;
    private Button mBtnOK;
    private ArrayAdapter<CharSequence> arrAdapSpnMaleAge;
    private ArrayAdapter<CharSequence> arrAdapSpnFemaleAge;
    private ArrayList<CheckBox> hobbies = new ArrayList<>();
    private TextView mTxtSug;
    private TextView mTxtHob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadGrp = (RadioGroup) findViewById(R.id.radSexGrp);
        arrAdapSpnMaleAge = ArrayAdapter.createFromResource(MainActivity.this, R.array.spnAgeMale,R.layout.support_simple_spinner_dropdown_item);
        arrAdapSpnFemaleAge = ArrayAdapter.createFromResource(MainActivity.this, R.array.spnAgeFemale,R.layout.support_simple_spinner_dropdown_item);
        mSpnAge = (Spinner) findViewById(R.id.spnAge);
        mTxtHob = (TextView) findViewById(R.id.txtHob);
        mBtnOK = (Button) findViewById(R.id.btnOK);
        mTxtSug = (TextView) findViewById(R.id.txtSug);
        mTxtHob = (TextView) findViewById(R.id.txtHob);

        hobbies.add((CheckBox) findViewById(R.id.checkBoxMusic));
        hobbies.add((CheckBox) findViewById(R.id.checkBoxSing));
        hobbies.add((CheckBox) findViewById(R.id.checkBoxDance));
        hobbies.add((CheckBox) findViewById(R.id.checkBoxTravel));
        hobbies.add((CheckBox) findViewById(R.id.checkBoxRead));
        hobbies.add((CheckBox) findViewById(R.id.checkBoxWriting));
        hobbies.add((CheckBox) findViewById(R.id.checkBoxClimbing));
        hobbies.add((CheckBox) findViewById(R.id.checkBoxSwimming));
        hobbies.add((CheckBox) findViewById(R.id.checkBoxeating));
        hobbies.add((CheckBox) findViewById(R.id.checkBoxDrawing));

        mBtnOK.setOnClickListener(btnOKOnClick);
        mRadGrp.setOnCheckedChangeListener(radioOnClick);
    }

    private RadioGroup.OnCheckedChangeListener radioOnClick = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.radSexBtn1:
                    mSpnAge.setAdapter(arrAdapSpnMaleAge);
                    break;
                case R.id.radSexBtn2:
                    mSpnAge.setAdapter(arrAdapSpnFemaleAge);
                    break;
            }
        }
    };

    /*private AdapterView.OnItemSelectedListener spnOnItemSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };*/

    private View.OnClickListener btnOKOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            MarriageSuggestion ms = new MarriageSuggestion();

            String strSex = "", strHob = "";
            int iAgeRange = 0;

            switch (mRadGrp.getCheckedRadioButtonId()){
                case R.id.radSexBtn1:
                    strSex = "male";
                    break;
                case R.id.radSexBtn2:
                    strSex = "female";
                    break;
            }
            switch (mSpnAge.getSelectedItem().toString()){
                case "小於30歲":
                    iAgeRange = 1;
                    break;
                case "30~35歲":
                    iAgeRange = 2;
                    break;
                case "大於35歲":
                    iAgeRange = 3;
                    break;
                case "小於28歲":
                    iAgeRange = 1;
                    break;
                case "28~32歲":
                    iAgeRange = 2;
                    break;
                case "大於32歲":
                    iAgeRange = 3;
                    break;
            }

            boolean isFirstHob = true;
            for (int i = 0;i < hobbies.size();i++)
            {
                if (hobbies.get(i).isChecked())
                {
                    if (!isFirstHob)
                        strHob += ",";
                    strHob += hobbies.get(i).getText();
                    isFirstHob = false;
                }
            }

            mTxtSug.setText(ms.getSuggestion(strSex, iAgeRange));
            mTxtHob.setText(ms.getHobbies(strHob));
        }
    };
}
