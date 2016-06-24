package com.sunking.it.moneybag;

/**
 * Created by ASUS on 4/26/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ASUS on 4/26/2016.
 */
public class TransactionHistoryCustomAdapter extends BaseAdapter {


    ArrayList<String> tidForAd;
    ArrayList<String> ridForAd;
    ArrayList<String> sidForAd;
    ArrayList<String> amountForAd;
    ArrayList<String> timeForAd;
    Context newContext;

    private static LayoutInflater newInflater;

    //constructor for this class mycustomAdapter
    public TransactionHistoryCustomAdapter(Context mainac, ArrayList<String> tid, ArrayList<String> rid, ArrayList<String> sid, ArrayList<String> amount, ArrayList<String> time){


        //nameForAd = name;//getting name from constructor parameter and saving in a nameForAd array defined in this class
        tidForAd = tid;
        ridForAd = rid;
        sidForAd = sid;
        amountForAd = amount;
        timeForAd = time;

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
        TextView htv_tid;
        TextView htv_rid;
        TextView htv_sid;
        TextView htv_amount;
        TextView htv_time;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        aHolder myh = new aHolder();
        View forView;

        forView = newInflater.inflate(R.layout.custom_layout_t_history,null);

        myh.htv_tid = (TextView) forView.findViewById(R.id.tv_tid);
        myh.htv_rid = (TextView) forView.findViewById(R.id.tv_rid);
        myh.htv_sid = (TextView) forView.findViewById(R.id.tv_sid);
        myh.htv_amount = (TextView) forView.findViewById(R.id.tv_amount);
        myh.htv_time = (TextView) forView.findViewById(R.id.tv_time);


        myh.htv_tid.setText(tidForAd.get(position));
        myh.htv_rid.setText(ridForAd.get(position));
        myh.htv_sid.setText(sidForAd.get(position));
        myh.htv_amount.setText(amountForAd.get(position));
        myh.htv_time.setText(timeForAd.get(position));

        return forView;
    }
}
