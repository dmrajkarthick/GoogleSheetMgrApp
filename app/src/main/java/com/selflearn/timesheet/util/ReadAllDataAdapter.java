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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.google.gson.Gson;
import com.selflearn.timesheet.R;
import com.selflearn.timesheet.activity.DeleteData;
import com.selflearn.timesheet.activity.ShareContentActivity;
import com.selflearn.timesheet.activity.UpdateData;

import java.util.ArrayList;

/**
 * Created by rkarthic on 14/03/18.
 */

public class ReadAllDataAdapter extends RecyclerView.Adapter<ReadAllDataAdapter.ViewHolder> implements Filterable{

    ArrayList<ReadAllDataModel> clientData, filterList;
    Context context;
    private LayoutInflater mInflater;
    Toolbar toolbar;
    CustomFilter filter;


    // Constructors
    public ReadAllDataAdapter(Context context, ArrayList<ReadAllDataModel> objects) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        clientData = objects;
        filterList = objects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.read_all_data_adapter, parent, false);
        toolbar = (Toolbar)view.findViewById(R.id.card_toolbar);
        toolbar.inflateMenu(R.menu.card_toolbar);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mDataModelObj = clientData.get(position);

        holder.jobType.setText("Job Type: " + clientData.get(position).getJobType());
        holder.client.setText("Client: " + clientData.get(position).getClient());
        holder.dateTime.setText("Date/Time: " + clientData.get(position).getDateTime());
        holder.siteName.setText("Site Name: " + clientData.get(position).getSiteName());
        holder.travelToSite.setText("Travel To Site: " + clientData.get(position).getTravelToSite());
        holder.travelFromSite.setText("Travel From Site: " + clientData.get(position).getTravelFromSite());
        holder.site_id_lr_code.setText("Site Code: " + clientData.get(position).getSite_id_lr_code());
        holder.odometerReading.setText("Odometer Reading: " + clientData.get(position).getOdometerReading());
        holder.kilometers.setText("Kilometers: " + clientData.get(position).getKilometers());
        holder.startTime.setText("Start Time: " + clientData.get(position).getStartTime());
        holder.endTime.setText("End Time: " + clientData.get(position).getEndTime());
        holder.breakTime.setText("Break Time: " + clientData.get(position).getBreakTime());
        holder.hoursOnSite.setText("Hours on Site: " + clientData.get(position).getHoursOnSite());


        toolbar.setOnMenuItemClickListener(
            new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Bundle basket;
                    Intent intent;
            switch (item.getItemId()) {
                case R.id.contentShare:
                    basket = new Bundle();
                    basket.putString("contentToBeShared", new Gson().toJson(clientData.get(position)));
                    intent = new Intent(context, ShareContentActivity.class);
                    intent.putExtras(basket);
                    context.startActivity(intent);
                    return true;
                case R.id.contentDelete:
                    basket = new Bundle();
                    basket.putString("contentToBeDeleted", new Gson().toJson(clientData.get(position)));
                    intent = new Intent(context, DeleteData.class);
                    intent.putExtras(basket);
                    context.startActivity(intent);
                    return true;
                case R.id.contentUpdate:
                    basket = new Bundle();
                    basket.putString("contentToBeUpdated", new Gson().toJson(clientData.get(position)));
                    intent = new Intent(context, UpdateData.class);
                    intent.putExtras(basket);
                    context.startActivity(intent);
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
        return clientData.size();
    }

    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new CustomFilter(filterList,this);
        }

        return filter;
    }




    class ViewHolder extends RecyclerView.ViewHolder {

        //public final TextView jobNumber;
        public final TextView dateTime;
        public final TextView client;
        public final TextView jobType;
        public final TextView site_id_lr_code;
        public final TextView siteName;
        public final TextView travelToSite;
        public final TextView travelFromSite;
        public final TextView odometerReading;
        public final TextView kilometers;
        public final TextView startTime;
        public final TextView endTime;
        public final TextView breakTime;
        public final TextView hoursOnSite;


        public final View mView;
        public ReadAllDataModel mDataModelObj;

        private ViewHolder(View view)
        {
            super(view);
            mView = view;
            this.client = (TextView)view.findViewById(R.id.tv_clientName);
            this.jobType = (TextView)view.findViewById(R.id.tv_jobType);
            this.dateTime = (TextView)view.findViewById(R.id.dateTime);
            this.siteName = (TextView)view.findViewById(R.id.siteName);
            this.travelToSite = (TextView)view.findViewById(R.id.travelToSite);
            this.travelFromSite = (TextView)view.findViewById(R.id.travelFromSite);
            this.site_id_lr_code = (TextView)view.findViewById(R.id.siteID_lr_code);
            this.odometerReading = (TextView)view.findViewById(R.id.tv_odometerReading);
            this.kilometers = (TextView)view.findViewById(R.id.tv_kms);
            this.startTime = (TextView)view.findViewById(R.id.tv_startTime);
            this.endTime = (TextView)view.findViewById(R.id.tv_endTime);
            this.breakTime = (TextView)view.findViewById(R.id.tv_breakTime);
            this.hoursOnSite = (TextView)view.findViewById(R.id.tv_hoursOnSite);

        }

    }

}
