package com.example.covidtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Model> {
    private Context mcontext;
    private List<Model> cData;

    public CustomAdapter(Context context, List<Model> cData) {
        super(context, R.layout.testing, cData);
        this.mcontext = context;
        this.cData = cData;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View listviewitem = inflater.inflate(R.layout.testing, null, true);
        TextView districtName = listviewitem.findViewById(R.id.districtName);
        TextView confirmed = listviewitem.findViewById(R.id.confirmedNumber);
        TextView active = listviewitem.findViewById(R.id.activeNumber);
        TextView deceased = listviewitem.findViewById(R.id.deceasedNumber);
        TextView recovered = listviewitem.findViewById(R.id.recoveredNumber);
//        TextView confirmeddelta = listviewitem.findViewById(R.id.confirmedDeltaNumber);
//        TextView recovereddelta = listviewitem.findViewById(R.id.recoveredDeltaNumber);
//        TextView deceaseddelta = listviewitem.findViewById(R.id.deceasedDeltaNumber);

        districtName.setText(cData.get(position).getDistrictName());
        confirmed.setText(cData.get(position).getConfirmed());
        active.setText(cData.get(position).getActive());
        deceased.setText(cData.get(position).getDeceased());
        recovered.setText(cData.get(position).getRecovered());
//        confirmeddelta.setText(cData.get(position).getConfirmeddelta());
//        recovereddelta.setText(cData.get(position).getRecovereddelta());
//        deceaseddelta.setText(cData.get(position).getDeceaseddelta());
        return listviewitem;

    }


}
