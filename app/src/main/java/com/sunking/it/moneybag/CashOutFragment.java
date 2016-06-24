package com.sunking.it.moneybag;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;


/**
 * A simple {@link Fragment} subclass.
 */
public class CashOutFragment extends Fragment {

    EditText cmID;
    EditText coAmount;
    Button cashOut;
    // String newBalance ="";

    public CashOutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cash_out, container, false);

        cmID = (EditText) view.findViewById(R.id.et_change_maker_ID);
        coAmount = (EditText) view.findViewById(R.id.et_cash_out_amount);

        cashOut = (Button) view.findViewById(R.id.btn_cash_out);


        cashOut.setOnClickListener(new View.OnClickListener()

        {


           /* final String opin = oldPin.getText().toString().trim();
            final String npin = newPin.getText().toString().trim();
            final String cpin = confirmPin.getText().toString().trim();*/


            @Override
            public void onClick(View v) {
                if (v == cashOut)


                    mCashOut();

                cmID.setText(null);
                coAmount.setText(null);


                // do something


            }


        });


        return view;
    }

    private void mCashOut() {

        final String receiverID = cmID.getText().toString().trim();
        final Double amountSendMoney = Double.valueOf(coAmount.getText().toString());
        Double usersBalance = Double.valueOf(Config.userInfo.get(7));

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //String email = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "Not Available");
        String myval = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "Not Available");
        String password = sharedPreferences.getString(Config.PASSWORD_SHARED_PREF, "Not Available");


       // String str = "12345";
        int[] id_array = new int[receiverID.length()];
        for (int i = 0; i < receiverID.length(); i++) {
            id_array[i] = Character.getNumericValue(receiverID.charAt(i));
        }
        Double amountCashOut = amountSendMoney +(amountSendMoney * 0.05);

        if (amountCashOut<= usersBalance && id_array[4] == 3) {



            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.CASHOUT_URL+"?Myval="+myval+"&Password="+password,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getContext().getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext().getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String, String>();
                    params.put(Config.KEY_SAMOUNT,String.valueOf(amountSendMoney));
                    params.put(Config.KEY_RECEIVERID,receiverID);
                    params.put(Config.KEY_USERID,Config.userInfo.get(8));
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity().getApplicationContext());
            requestQueue.add(stringRequest);

            Toast.makeText(getContext().getApplicationContext(), "Dada Passing.....",
                    Toast.LENGTH_LONG).show();



        }

        else if(amountCashOut > usersBalance)
        {
            Toast.makeText(getContext().getApplicationContext(), "You do not have " + Double.toString(amountCashOut) + " Tk in your account",
                    Toast.LENGTH_LONG).show();

        }

        else
        {
            Toast.makeText(getContext().getApplicationContext(), "This is not a Change Maker's ID",
                    Toast.LENGTH_LONG).show();

        }




    }


}