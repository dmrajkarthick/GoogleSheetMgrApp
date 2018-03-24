package com.selflearn.timesheet.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.selflearn.timesheet.R;
import com.selflearn.timesheet.activity.ShareContentActivity;

import java.util.List;

/**
 * Created by rkarthic on 14/03/18.
 */

public class ReadAllDataAdapter extends RecyclerView.Adapter<ReadAllDataAdapter.ViewHolder> {

    List<ReadAllDataModel> mValues;
    Context context;
    private LayoutInflater mInflater;
    Toolbar toolbar;


    // Constructors
    public ReadAllDataAdapter(Context context, List<ReadAllDataModel> objects) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        mValues = objects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.read_all_data_adapter, parent, false);
        toolbar = (Toolbar)view.findViewById(R.id.card_toolbar);
        toolbar.inflateMenu(R.menu.card_toolbar);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mDataModelObj = mValues.get(position);
        holder.textViewName.setText(mValues.get(position).getClient());
        holder.textViewId.setText(mValues.get(position).getJob_number());


        toolbar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.contentShare:
                                Bundle basket = new Bundle();
                                basket.putString("contentToBeShared", "Item Name: " );
                                Intent a = new Intent(context, ShareContentActivity.class);
                                a.putExtras(basket);
                                context.startActivity(a);
                                return true;
                            case R.id.contentBookmark:
                                /*TODO: Think of a way to store the bookmarked data. Either in house DB or In the excel itself*/
                                return true;
                        }
                        return false;
                    }
                }
        );

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView textViewId;
        public final TextView textViewName;
        public final View mView;
        public ReadAllDataModel mDataModelObj;

        private ViewHolder(View view)
        {
            super(view);
            mView = view;
            this.textViewId = (TextView)view.findViewById(R.id.content);
            this.textViewName = (TextView)view.findViewById(R.id.maincontent);

        }

    }

}
