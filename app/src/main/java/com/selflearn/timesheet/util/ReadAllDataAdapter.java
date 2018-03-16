package com.selflearn.timesheet.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by rkarthic on 14/03/18.
 */

public class ReadAllDataAdapter extends ArrayAdapter<ReadAllDataModel> {

    List<ReadAllDataModel> modelList;
    Context context;
    private LayoutInflater mInflater;


    // Constructors
    public ReadAllDataAdapter(Context context, List<ReadAllDataModel> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
    }
}
