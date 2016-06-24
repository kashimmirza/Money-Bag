package com.sunking.it.moneybag;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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


public class HomeFragment extends Fragment {

    TextView textView;
    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);


       mupdateMoney();

        textView = (TextView) view.findViewById(R.id.textViewUsername);
        Config.curBalance = (TextView) view. findViewById(R.id.et_current_balance);


        textView.setText("Name: " +Config.userInfo.get(0)+"\n\n"+"ID: "+ Config.userInfo.get(8));

        Config.curBalance.setText((Config.userInfo.get(7) + " Tk"));

        return view;
    }

    private void mupdateMoney()
    {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //String email = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "Not Available");
        String myval = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "Not Available");
        String password = sharedPreferences.getString(Config.PASSWORD_SHARED_PREF, "Not Available");


        String url = Config.PROFILE_URL+"?Myval="+myval+"&Password="+password;



        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("my data", response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            String jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject personUser = (JSONObject) response
                                        .get(i);
                                Config.userInfo.clear();
                                Config.userInfo.add( personUser.getString("name"));// User's Name in index (0)
                                Config.userInfo.add(personUser.getString("phone"));// User's Phone in index (1)
                                Config.userInfo.add(personUser.getString("dob"));// User's DOB in index (2)
                                Config.userInfo.add(personUser.getString("profession"));// User's Prof in index (3)
                                Config.userInfo.add( personUser.getString("present_add"));// User's Present Address in index (4)
                                Config.userInfo.add(String.valueOf(personUser.getInt("pin")));// User's PIN in index (5)
                                Config.userInfo.add(personUser.getString("password"));// User's Password in index (6)
                                Config.userInfo.add(personUser.getString("balance"));// User's Current Balance in index (7)
                                Config.userInfo.add(personUser.getString("userId"));// User's ID in index (8)
                                Config.userInfo.add(personUser.getString("email"));// Email ID in index (9)

                            }

                            textView.setText(Config.userInfo.get(0)+"\n\n"+"ID: "+ Config.userInfo.get(8));
                            Config.curBalance.setText((Config.userInfo.get(7) + " Tk"));

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






    }


}
