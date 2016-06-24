package com.sunking.it.moneybag;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiveMoneyFragment extends Fragment {




    ListView myList;



    public static final ArrayList<String> fromActIdRM = new ArrayList<String>();
    public static final ArrayList<String> fromAcsIdRM = new ArrayList<String>();
    public static final ArrayList<String> fromAcAmountRM = new ArrayList<String>();
    public static final ArrayList<String> fromAcTimeRM = new ArrayList<String>();



    public ReceiveMoneyFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_receive_money, container, false);


        myList= (ListView) view.findViewById(R.id.l_r_money);


        fromActIdRM.clear();
        fromAcsIdRM.clear();
        fromAcAmountRM.clear();
        fromAcTimeRM.clear();




        final ReceivedMoneyCA aAdapter = new ReceivedMoneyCA(this.getActivity(),fromActIdRM,fromAcsIdRM,fromAcAmountRM,fromAcTimeRM);

        String url = Config.RECEIVEDMONEY_URL+"?userID=" + Config.userInfo.get(8);



        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("my data", response.toString());

                        try {



                            // Parsing json array response
                            // loop through each json object


                            for (int i = 0; i < response.length(); i++) {

                                JSONObject personUser = (JSONObject) response
                                        .get(i);



                                fromActIdRM.add(personUser.getString("tId"));


                                fromAcsIdRM.add(personUser.getString("sId"));

                                fromAcAmountRM.add(personUser.getString("amount"));

                                fromAcTimeRM.add(personUser.getString("time"));


                            }
                            myList.setAdapter(aAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext().getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(",y error", "Error: " + error.getMessage());
                Toast.makeText(getContext().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        // Adding request to request queue

        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity().getApplicationContext());
        requestQueue.add(req);



        return view;
    }

}
