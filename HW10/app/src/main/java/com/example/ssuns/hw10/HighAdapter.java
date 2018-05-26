package com.example.ssuns.hw10;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

class HighlightAdapter<T> extends ArrayAdapter<T> {

    private ArrayList<Integer> indexList;

    public HighlightAdapter(Context context, int resource) {
        super(context, resource);
        indexList = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final View renderer = super.getView(position, convertView, parent);
        if (indexList.contains(position)) {
            renderer.setBackgroundResource(R.color.selected);
        }
        else {
            renderer.setBackgroundResource(R.color.normal);
        }
        return renderer;
    }

    public void setHighlightList() { indexList.clear(); }

    public void setHighlightList(ArrayList<Integer> newIndexList) {
        indexList = newIndexList;
    }
}

