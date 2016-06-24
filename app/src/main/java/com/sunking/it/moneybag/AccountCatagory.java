package com.sunking.it.moneybag;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class AccountCatagory extends AppCompatActivity implements View.OnClickListener {

    Button buttonContinue;
    RadioGroup radioGroup;
    String value ="";
    boolean checked  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_catagory);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupAccount);
        buttonContinue = (Button) findViewById(R.id.btn_continue);

        buttonContinue.setOnClickListener(this);

    }



    public void AccountTypeClicked(View view) {

        int min = 0;
        int max = 9999999;
        Random r = new Random();
        Calendar c=Calendar.getInstance();
        int mYear=c.get(Calendar.YEAR);
        int i = r.nextInt(max - min + 1) + min;

        String selectedID = "";

// Check that the button is  now checked?
        checked = ((RadioButton) view).isChecked();

// Check which radio button was clicked
        switch (view.getId()) {
            case R.id.personalAccount:
                if (checked)
                    selectedID = "1";
                Toast.makeText(AccountCatagory.this, "Personal Account", Toast.LENGTH_LONG).show();
                value = mYear+selectedID+i;

                //Toast.makeText(getApplicationContext(), "Your ID is " +value,  Toast.LENGTH_SHORT).show();

                break;

            case R.id.Trade:
                if (checked)
                    selectedID = "2";
                Toast.makeText(AccountCatagory.this, "Trade Account", Toast.LENGTH_LONG).show();
                value = mYear+selectedID+i;

                //  Toast.makeText(getApplicationContext(), "Your ID is " +value,  Toast.LENGTH_SHORT).show();


                break;

            case R.id.Change_Maker:
                if (checked)
                    selectedID = "3";
                Toast.makeText(AccountCatagory.this, "Change Maker Account", Toast.LENGTH_LONG).show();
                value = mYear+selectedID+i;

                //Toast.makeText(getApplicationContext(), "Your ID is " +value,  Toast.LENGTH_SHORT).show();


                break;
        }
    }





    public void AccountType(){
        Intent intent = new Intent(this, Registration.class);
        intent.putExtra("ID", value);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonContinue && checked) {
            AccountType();
        } else  {
            Toast.makeText(getApplicationContext(), "Select your account type ",  Toast.LENGTH_SHORT).show();
        }
    }

}
