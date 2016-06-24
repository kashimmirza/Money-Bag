package com.sunking.it.moneybag;

/**
 * Created by ASUS on 4/27/2016.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ReceivedMoneyCA extends BaseAdapter {


    ArrayList<String> tidForAd;
    //ArrayList<String> ridForAd;
    ArrayList<String> sidForAd;
    ArrayList<String> amountForAd;
    ArrayList<String> timeForAd;
    Context newContext;

    private static LayoutInflater newInflater;

    //constructor for this class mycustomAdapter
    public ReceivedMoneyCA(Context mainac, ArrayList<String> tid_rm, ArrayList<String> sid_rm, ArrayList<String> amount_rm, ArrayList<String> time_rm){


        //nameForAd = name;//getting name from constructor parameter and saving in a nameForAd array defined in this class
        tidForAd = tid_rm;
       // ridForAd = rid;
        sidForAd = sid_rm;
        amountForAd = amount_rm;
        timeForAd = time_rm;

        newContext= mainac;

        newInflater = (LayoutInflater)newContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return tidForAd.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class aHolder{
        TextView htv_tid_rm;
       // TextView htv_rid;
        TextView htv_sid_rm;
        TextView htv_amount_rm;
        TextView htv_time_rm;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        aHolder myh = new aHolder();
        View forView;

        forView = newInflater.inflate(R.layout.received_money_custom_layout,null);

        myh.htv_tid_rm = (TextView) forView.findViewById(R.id.tv_tid_rm);
       // myh.htv_rid = (TextView) forView.findViewById(R.id.tv_rid);
        myh.htv_sid_rm = (TextView) forView.findViewById(R.id.tv_sid_rm);
        myh.htv_amount_rm = (TextView) forView.findViewById(R.id.tv_amount_rm);
        myh.htv_time_rm = (TextView) forView.findViewById(R.id.tv_time_rm);


        myh.htv_tid_rm.setText(tidForAd.get(position));
       // myh.htv_rid.setText(ridForAd.get(position));
        myh.htv_sid_rm.setText(sidForAd.get(position));
        myh.htv_amount_rm.setText(amountForAd.get(position));
        myh.htv_time_rm.setText(timeForAd.get(position));

        return forView;
    }
}

