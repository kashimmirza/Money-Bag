package com.sunking.it.moneybag;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePinFragment extends Fragment {

    EditText oldPin;
    EditText newPin;
    EditText confirmPin;
    Button changePin;



    private final Pattern PIN_PATTERN = Pattern
            .compile("[0-9]{4}");


    public ChangePinFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_pin, container, false);
        oldPin = (EditText) view.findViewById(R.id.et_old_PIN);
        newPin = (EditText) view.findViewById(R.id.et_new_PIN);
        confirmPin = (EditText) view.findViewById(R.id.et_confirm_PIN);
        changePin = (Button) view.findViewById(R.id.change_pin);



        changePin.setOnClickListener(new View.OnClickListener()

        {


           /* final String opin = oldPin.getText().toString().trim();
            final String npin = newPin.getText().toString().trim();
            final String cpin = confirmPin.getText().toString().trim();*/


            @Override
            public void onClick(View v) {
                if(v==changePin)

                    changePin();

                oldPin.setText(null);
                newPin.setText(null);
                confirmPin.setText(null);
                // do something


            }


        });


        return view ;
    }

    private void changePin() {


        final String opin = oldPin.getText().toString().trim();
        final String pin = newPin.getText().toString().trim();
        final String cpin = confirmPin.getText().toString().trim();
        final String mpin = Config.userInfo.get(5);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //String email = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "Not Available");
        String myval = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "Not Available");
        String password = sharedPreferences.getString(Config.PASSWORD_SHARED_PREF, "Not Available");



        if (!opin.equals(mpin)) {
            Toast.makeText(ChangePinFragment.this.getActivity().getApplicationContext(), "Pin did not match with old pin",
                    Toast.LENGTH_LONG).show();
        } else if (pin.equals("")) {
            Toast.makeText(ChangePinFragment.this.getActivity().getApplicationContext(), "Pin can not be empty",
                    Toast.LENGTH_LONG).show();

        } else if (!CheckPINNumber(pin) ) {
            Toast.makeText(ChangePinFragment.this.getActivity().getApplicationContext(), "Use number as pin [0-9] and pin limit is 4",
                    Toast.LENGTH_LONG).show();
        }else  if (!cpin.equals(pin)) {
            Toast.makeText(ChangePinFragment.this.getActivity().getApplicationContext(), "Pin did not matched",
                    Toast.LENGTH_LONG).show();

        }

        else{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.CHANGEPIN_URL+"?Myval="+myval+"&Password="+password,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getContext().getApplicationContext(),response,Toast.LENGTH_LONG).show();
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
                    params.put(Config.KEY_NEWPIN,pin);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity().getApplicationContext());
            requestQueue.add(stringRequest);

            Toast.makeText(getContext().getApplicationContext(), "Dada Passing.....",
                    Toast.LENGTH_LONG).show();

        }



    }

    private boolean CheckPINNumber(String pin) {

        return PIN_PATTERN.matcher(pin).matches();
    }


}