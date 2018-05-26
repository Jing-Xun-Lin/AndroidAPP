package com.example.ssuns.hw10;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchContact extends Fragment {

    private ListView contactList;
    private HighlightAdapter<String> dataAdapter;

    public SearchContact() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_contact, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        contactList = view.findViewById(R.id.contactList);
        contactList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        contactList.clearChoices();

        dataAdapter = new HighlightAdapter<>(this.getContext(), android.R.layout.simple_list_item_1);
        contactList.setAdapter(dataAdapter);

        Cursor cursor = MainActivity.sdbContact.rawQuery("select * from " + MainActivity.DB_TABLE,null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String data = "Name: " + cursor.getString(1) +
                        ", PhoneNumber: " + cursor.getString(2) +
                        ", PhoneType: " + cursor.getString(3);
                dataAdapter.add(data);
                cursor.moveToNext();
            }
            cursor.close();
        }
        dataAdapter.notifyDataSetChanged();
    }

    public void addData(String data) {
        dataAdapter.add(data);
        dataAdapter.notifyDataSetChanged();
        contactList.clearChoices();
        contactList.requestLayout();
    }

    public void setListHighlight() {
        dataAdapter.setHighlightList();
    }

    public void setListHighlight(ArrayList<String> list){
        ArrayList<Integer> indexList = new ArrayList<>();
        int length = dataAdapter.getCount();

        for (int i = 0; i < length; i++) {
            if (list.contains(dataAdapter.getItem(i))) {
                indexList.add(i);
            }
        }

        dataAdapter.setHighlightList(indexList);
    }
}
