package com.selflearn.timesheet.util;

import android.widget.Filter;

import java.util.ArrayList;

/**
 * Created by rkarthic on 29/07/18.
 */

public class CustomFilter extends Filter {

    ReadAllDataAdapter adapter;
    ArrayList<ReadAllDataModel> filterList;

    public CustomFilter(ArrayList<ReadAllDataModel> filterList,ReadAllDataAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<ReadAllDataModel> filteredPlayers=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getClient().toUpperCase().contains(constraint)
                        || filterList.get(i).getJobType().toUpperCase().contains(constraint)
                        || filterList.get(i).getSiteName().toUpperCase().contains(constraint)
                        || filterList.get(i).getTravelToSite().toUpperCase().contains(constraint)
                        || filterList.get(i).getTravelFromSite().toUpperCase().contains(constraint)
                        || filterList.get(i).getDateTime().toUpperCase().contains(constraint)
                        || filterList.get(i).getSite_id_lr_code().toUpperCase().contains(constraint)
                        || filterList.get(i).getOdometerReading().toUpperCase().contains(constraint)
                        || filterList.get(i).getKilometers().toUpperCase().contains(constraint)
                        || filterList.get(i).getBreakTime().toUpperCase().contains(constraint)
                        || filterList.get(i).getStartTime().toUpperCase().contains(constraint)
                        || filterList.get(i).getEndTime().toUpperCase().contains(constraint)
                        || filterList.get(i).getHoursOnSite().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPlayers.add(filterList.get(i));
                }
            }

            results.count=filteredPlayers.size();
            results.values=filteredPlayers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.clientData = (ArrayList<ReadAllDataModel>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
