package com.example.ssuns.hw10;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

public class AddNewContact extends Fragment {

    EditText edtName, edtPhoneNum;
    Spinner spnPhoneType;

    public AddNewContact() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_new_contact, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        View view = getView();

        edtName = view.findViewById(R.id.edtName);
        edtPhoneNum = view.findViewById(R.id.edtPhoneNumber);
        spnPhoneType = view.findViewById(R.id.spinPhoneNumberType);
    }

    public ContentValues getContentValues() {
        ContentValues newData = new ContentValues();
        newData.put("name", edtName.getText().toString());
        newData.put("phoneNumber", edtPhoneNum.getText().toString());
        newData.put("phoneType", spnPhoneType.getSelectedItem().toString());
        return newData;
    }
}
