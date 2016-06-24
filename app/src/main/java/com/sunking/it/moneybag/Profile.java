package com.sunking.it.moneybag;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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

public class Profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private boolean loggedIn = false;
    TextView textView;
    NavigationView navigationView = null;
    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Config.curBalance = (TextView) findViewById(R.id.et_current_balance);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView) findViewById(R.id.textViewUsername);
        Intent intent = getIntent();

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String myval = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "Not Available");

        String password = sharedPreferences.getString(Config.PASSWORD_SHARED_PREF, "Not Available");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // fetching data


        // Tag used to cancel the request
        String tag_json_arry = "json_array_req";

        //String url = Config.PROFILE_URL+myval;
        ;
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

                            textView.setText("Name: " +Config.userInfo.get(0)+"\n\n"+"ID: "+ Config.userInfo.get(8));
                            Config.curBalance.setText((Config.userInfo.get(7) + " Tk"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(",y error", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        // Adding request to request queue

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(req);

        // Toast.makeText(getApplicationContext(), "Data loaded sucessfully!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        /*
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Would you like to exit?");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(Profile.this, "You clicked yes button", Toast.LENGTH_LONG).show();
                SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                //Getting editor
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(Profile.this, Login.class);
                startActivity(intent);

            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Profile.this, "You clicked No button", Toast.LENGTH_LONG).show();
                //finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
       final int id = item.getItemId();

        View v = LayoutInflater.from(Profile.this).inflate(R.layout.alart_pin_check_layout, null);
        final EditText alertPin = (EditText) v.findViewById(R.id.ad_check_pin);

        if(id == R.id.nav_UserProfile || id ==R.id.nav_ChangePassword || id ==R.id.nav_ChangePin || id ==R.id.nav_EditProfile
                || id ==R.id.nav_ReceiveMoney || id ==R.id.nav_TransactionHistory || id ==R.id.nav_SendMoney ||id ==R.id.nav_CashOut )

        {



            AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
            builder.setMessage("Enter Pin")
                    .setView(v)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String aPin = alertPin.getText().toString().trim();

                            if (aPin.equals(Config.userInfo.get(5))) {




                                if (id == R.id.nav_UserProfile) {
                                    //set the fragment initially
                                    UserProfileFragment fragment = new UserProfileFragment();
                                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                                            getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                                    fragmentTransaction.commit();

                                    // Handle the camera action
                                } else if (id == R.id.nav_SendMoney) {
                                    //set the fragment initially
                                    SendMoneyFragment fragment = new SendMoneyFragment();
                                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                                            getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                                    fragmentTransaction.commit();

                                }
                                else if (id == R.id.nav_ReceiveMoney) {
                                    //set the fragment initially
                                    ReceiveMoneyFragment fragment = new ReceiveMoneyFragment();
                                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                                            getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                                    fragmentTransaction.commit();
                                }
                                else if (id == R.id.nav_TransactionHistory) {
                                    TransactionHistoryFragment fragment = new TransactionHistoryFragment();
                                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                                            getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                                    fragmentTransaction.commit();

                                } else if (id == R.id.nav_EditProfile) {
                                    EditProfileFragment fragment = new EditProfileFragment();
                                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                                            getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                                    fragmentTransaction.commit();

                                } else if (id == R.id.nav_ChangePassword) {
                                    ChangePasswordFragment fragment = new ChangePasswordFragment();
                                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                                            getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                                    fragmentTransaction.commit();

                                } else if (id == R.id.nav_ChangePin) {
                                    ChangePinFragment fragment = new ChangePinFragment();
                                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                                            getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                                    fragmentTransaction.commit();

                                }else if (id == R.id.nav_CashOut) {
                                    CashOutFragment fragment = new CashOutFragment();
                                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                                            getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                                    fragmentTransaction.commit();

                                }


                            }

                            else {

                                Toast.makeText(Profile.this, "Pin did not match",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    })
                    .setNegativeButton("Cancel",null)
                    .setCancelable(false);
            AlertDialog alert = builder.create();
            alert.show();


        }



        else if (id == R.id.nav_Home) {
            //set the fragment initially
            HomeFragment fragment = new HomeFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();


        }
        else if (id == R.id.nav_LogOut) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Would you like to logout?");

            alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(Profile.this, "You clicked yes button", Toast.LENGTH_LONG).show();
                    SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    //Getting editor
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.commit();
                    Config.userInfo.clear();
                    Intent intent = new Intent(Profile.this, Login.class);
                    startActivity(intent);

                }
            });

            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(Profile.this, "You clicked No button", Toast.LENGTH_LONG).show();
                    //finish();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();





        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(!loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
    }
}