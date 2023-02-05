package com.example.testing;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class H_ListAdapter extends ArrayAdapter {

    private Activity mContext;
    List<H_Object> h_objectList;

    public H_ListAdapter(Activity mContext, List<H_Object> h_objectList){
        super(mContext,R.layout.list_item,h_objectList);
        this.mContext = mContext;
        this.h_objectList =h_objectList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item,null,true);

        TextView v_location = listItemView.findViewById(R.id.v_location);
        TextView v_description = listItemView.findViewById(R.id.v_description);
        TextView v_facilities = listItemView.findViewById(R.id.v_facilities);
        TextView v_offer = listItemView.findViewById(R.id.v_offer);

        H_Object h_object = h_objectList.get(position);

        v_location.setText(h_object.getLocation());
        v_description.setText(h_object.getDescription());
        v_facilities.setText(h_object.getFacilities());
        v_offer.setText(h_object.getOffer());

        return listItemView;

    }
}
