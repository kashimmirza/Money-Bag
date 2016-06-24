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

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {

    // private boolean loggedIn = false;
    TextView user_name;
    TextView user_email;
    TextView user_phone;
    TextView user_dob;
    TextView user_profession;
    TextView user_PresentAdd;


    public UserProfileFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);


        user_name = (TextView) view.findViewById(R.id.et_user_name);
        user_email = (TextView) view.findViewById(R.id.et_email_address);
        user_phone = (TextView) view.findViewById(R.id.et_phone_number);
        user_dob = (TextView) view.findViewById(R.id.uBirth);
        user_profession = (TextView) view.findViewById(R.id.uProfession);
        user_PresentAdd = (TextView) view.findViewById(R.id.uPrAddress);


        user_name.setText("Name : " +Config.userInfo.get(0));
        user_email.setText("Email : " + Config.userInfo.get(9));
        user_phone.setText("Phone : "+ Config.userInfo.get(1));
        user_dob.setText("Date of Birth : " +Config.userInfo.get(2));
        user_profession.setText("Profession : " +Config.userInfo.get(3));
        user_PresentAdd.setText("Present_Address : "+ Config.userInfo.get(4));


        return view ;


    }

}