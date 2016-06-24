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
public class TransactionHistoryFragment extends Fragment {


    ListView myList;



    public static final ArrayList<String> fromActId = new ArrayList<String>();
    public static final ArrayList<String> fromAcrId = new ArrayList<String>();
    public static final ArrayList<String> fromAcsId = new ArrayList<String>();
    public static final ArrayList<String> fromAcAmount = new ArrayList<String>();
    public static final ArrayList<String> fromAcTime = new ArrayList<String>();


    public TransactionHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_transaction_history, container, false);



        myList= (ListView) view.findViewById(R.id.l_t_history);




        fromActId.clear();
        fromAcrId.clear();
        fromAcsId.clear();
        fromAcAmount.clear();
        fromAcTime.clear();


        final TransactionHistoryCustomAdapter aAdapter = new TransactionHistoryCustomAdapter(this.getActivity(),fromActId,fromAcrId,fromAcsId,fromAcAmount,fromAcTime);
        //myList.setAdapter(aAdapter);



        String url = Config.THISTORY_URL+"?userID=" + Config.userInfo.get(8);



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



                                fromActId.add(personUser.getString("tId"));

                                fromAcrId.add(personUser.getString("rId"));

                                fromAcsId.add(personUser.getString("sId"));

                                fromAcAmount.add(personUser.getString("amount"));

                                fromAcTime.add(personUser.getString("time"));


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




        return view ;
        // Inflate the layout for this fragment
    }

}
