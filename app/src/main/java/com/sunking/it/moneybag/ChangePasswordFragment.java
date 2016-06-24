package com.sunking.it.moneybag;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends Fragment {

    EditText oldPass;
    EditText newPass;
    EditText confirmPass;
    Button changePass;

    private static final Pattern PASSWORD_PATTERN = Pattern
            .compile("[(?=.*d)(?=.*[A-Za-z])[0-9A-Za-z!@#$%]]{6,30}");

    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        oldPass = (EditText) view.findViewById(R.id.et_old_password);
        newPass = (EditText) view.findViewById(R.id.et_new_password);
        confirmPass = (EditText) view.findViewById(R.id.et_confirm_password);
        changePass = (Button) view.findViewById(R.id.change_password);


        changePass.setOnClickListener(new View.OnClickListener()

        {


           /* final String opin = oldPin.getText().toString().trim();
            final String npin = newPin.getText().toString().trim();
            final String cpin = confirmPin.getText().toString().trim();*/


            @Override
            public void onClick(View v) {
                if(v==changePass)

                    changePass();
                oldPass.setText(null);
                newPass.setText(null);
                confirmPass.setText(null);



                // do something


            }


        });


        return view;
    }

    private void changePass() {


        final String opass = oldPass.getText().toString().trim();
        final String newpassword = newPass.getText().toString().trim();
        final String cpass = confirmPass.getText().toString().trim();
       // final String mpass = Config.userInfo.get(6);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //String email = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "Not Available");
        String myval = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "Not Available");
        String password = sharedPreferences.getString(Config.PASSWORD_SHARED_PREF, "Not Available");



        if (!opass.equals(password)) {
            Toast.makeText(ChangePasswordFragment.this.getActivity().getApplicationContext(), "Password did not match with old password" + Config.userInfo.get(6),
                    Toast.LENGTH_LONG).show();
        } else if (newpassword.equals("")) {
            Toast.makeText(ChangePasswordFragment.this.getActivity().getApplicationContext(), "Password can not be empty",
                    Toast.LENGTH_LONG).show();

        } else if (!CheckPassword(newpassword) ) {
            Toast.makeText(ChangePasswordFragment.this.getActivity().getApplicationContext(), "Password should be at least 6 character. No space allowed",
                    Toast.LENGTH_LONG).show();
        }else  if (!cpass.equals(newpassword)) {
            Toast.makeText(ChangePasswordFragment.this.getActivity().getApplicationContext(), "Password did not matched",
                    Toast.LENGTH_LONG).show();

        }

        else{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.CHANGEPASS_URL+"?Myval="+myval+"&Password="+password,
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
                    params.put(Config.KEY_NEWPASSWORD,newpassword);

                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity().getApplicationContext());
            requestQueue.add(stringRequest);

            Toast.makeText(getContext().getApplicationContext(), "Dada Passing.....",
                    Toast.LENGTH_LONG).show();

        }



    }
    private boolean CheckPassword(String password) {

        return PASSWORD_PATTERN.matcher(password).matches();
    }



}