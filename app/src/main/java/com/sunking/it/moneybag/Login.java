package com.sunking.it.moneybag;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener{

    EditText editTextEmail;
    TextView tv_rp_forgotPassword;

    EditText editTextPassword;
    Button buttonLogin;
    Button buttonSignup;
    private ProgressDialog loading;

    String s = "";
    String rec_text;
    String myval="";
    String email,phone;
    CheckBox check;
    String password;
    private boolean loggedIn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        tv_rp_forgotPassword = (TextView) findViewById(R.id.text_view_forgotPassword);
        check = (CheckBox) findViewById(R.id.checkBox1);


        buttonLogin = (Button) findViewById(R.id.btn_login);
        buttonSignup = (Button) findViewById(R.id.btn_signup);

        buttonLogin.setOnClickListener(this);
        buttonSignup.setOnClickListener(this);
        tv_rp_forgotPassword.setOnTouchListener(this);


        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
// TODO Auto-generated method stub

                if (!isChecked) {
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });


    }



    private void userLogin() {


        rec_text = editTextEmail.getText().toString().trim();

        password = editTextPassword.getText().toString().trim();

        if(checkifmail(rec_text)==true) {
            myval = rec_text;
            email = rec_text;
            //Do whatever you like.

        }
        else {
            myval =rec_text;
            phone = rec_text;

        }

        Log.d("data", "My vale is "+myval);






        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.LOGIN_URL+"?Myval="+myval+"&Password="+password,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){

                            openProfile();
                            SharedPreferences sharedPreferences = Login.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Config.EMAIL_SHARED_PREF, myval);
                            editor.putString(Config.PASSWORD_SHARED_PREF, password);


                            //Saving values to editor
                            editor.commit();


                        }else{
                            Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("Myval",myval);
                map.put(Config.KEY_PASSWORD,password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void openProfile(){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        if (v == buttonLogin){
            userLogin();
        }
        if (v == buttonSignup){
            startActivity(new Intent(this, AccountCatagory.class));
        }

    }


    public boolean checkifmail(String s){

        boolean b = false;

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '@'){
                b =  true;
            }
        }
        return b;

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Intent i = new Intent(this, ForgotPassword.class);
        startActivity(i);
        return false;

    }


}
