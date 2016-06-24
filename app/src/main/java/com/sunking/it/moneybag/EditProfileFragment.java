package com.sunking.it.moneybag;


import android.content.Context;
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
public class EditProfileFragment extends Fragment {

    EditText eName;
    EditText ePrAdd;
    EditText eProf;
    Button eSave;
    Button eCancel;

    private static final Pattern NAME_PATTERN = Pattern
            .compile("[A-Za-z ]{1,250}");


    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_edit_profile, container, false);

        eName = (EditText) view.findViewById(R.id.et_eName);
        ePrAdd = (EditText) view.findViewById(R.id.et_ePrAdd);
        eProf = (EditText) view.findViewById(R.id.et_eProf);
        eSave = (Button)view.findViewById(R.id.save_edit);
        eCancel = (Button)view.findViewById(R.id.cancel_edit);



        eSave.setOnClickListener(new View.OnClickListener()

        {


           /* final String opin = oldPin.getText().toString().trim();
            final String npin = newPin.getText().toString().trim();
            final String cpin = confirmPin.getText().toString().trim();*/


            @Override
            public void onClick(View v) {
                if (v == eSave)

                    editProfile();



                eName.setText(null);
                ePrAdd.setText(null);
                eProf.setText(null);
                // do something


            }


        });

        eCancel.setOnClickListener(new View.OnClickListener()

        {


           /* final String opin = oldPin.getText().toString().trim();
            final String npin = newPin.getText().toString().trim();
            final String cpin = confirmPin.getText().toString().trim();*/


            @Override
            public void onClick(View v) {
                if (v == eCancel)

                    eName.setText(null);
                    ePrAdd.setText(null);
                    eProf.setText(null);


                // do something


            }


        });


        return view;
    }


    private void editProfile() {

        final String editName = eName.getText().toString().trim();
        final String editPrAdd = ePrAdd.getText().toString().trim();
        final String editProf = eProf.getText().toString().trim();

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //String email = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "Not Available");
        String myval = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "Not Available");
        String password = sharedPreferences.getString(Config.PASSWORD_SHARED_PREF, "Not Available");


        if (editName.equals("") && editPrAdd.equals("") && editProf.equals("")) {
            Toast.makeText(EditProfileFragment.this.getActivity().getApplicationContext(), "Name can not be empty",
                    Toast.LENGTH_LONG).show();

        } else if (!CheckUsername(editName)) {
            Toast.makeText(EditProfileFragment.this.getActivity().getApplicationContext(), "Only letters and white space allowed",
                    Toast.LENGTH_LONG).show();
        }
        else if (editPrAdd.equals("")) {
            Toast.makeText(EditProfileFragment.this.getActivity().getApplicationContext(), "Address can not be empty",
                    Toast.LENGTH_LONG).show();

        }

        if ( editProf.equals("")) {
            Toast.makeText(EditProfileFragment.this.getActivity().getApplicationContext(), "Profession can not be empty",
                    Toast.LENGTH_LONG).show();

        }
        else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.EDITPROFILE_URL + "?Myval=" + myval + "&Password=" + password,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getContext().getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(Config.KEY_NEWNAME, editName);
                    params.put(Config.KEY_NEWPRESENTADDRESS,editPrAdd);
                    params.put(Config.KEY_NEWPROFESSION, editProf);

                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity().getApplicationContext());
            requestQueue.add(stringRequest);

            Toast.makeText(getContext().getApplicationContext(), "Dada Passing.....",
                    Toast.LENGTH_LONG).show();


        }
    }

    private boolean CheckUsername(String username) {

        return NAME_PATTERN.matcher(username).matches();
    }
}
