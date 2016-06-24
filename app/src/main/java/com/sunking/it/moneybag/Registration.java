package com.sunking.it.moneybag;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    static final int DATE_DIALOG_ID = 0;
    private int mYear,mMonth,mDay;
    String selectedID = "";

    private static final Pattern EMAIL_PATTERN = Pattern
            .compile("[a-zA-Z0-9._%-+]{1,100}" + "@"
                    + "[a-zA-Z]{0,10}" + "." + "[a-zA-Z]{0,20}");
    private static final Pattern NAME_PATTERN = Pattern
            .compile("[A-Za-z ]{1,250}");
    private static final Pattern PASSWORD_PATTERN = Pattern
            .compile("[(?=.*d)(?=.*[A-Za-z])[0-9A-Za-z!@#$%]]{6,30}");
    private static final Pattern PHONE_PATTERN = Pattern
            .compile("[0-9]{11}");

    //private static final Pattern PROFESSION_PATTERN = Pattern
    //      .compile("[A-Za-z]");

    private static final Pattern ID_PATTERN = Pattern
            .compile("[A-Za-z0-9 ]{9,17}");
    private static final Pattern PIN_PATTERN = Pattern
            .compile("[0-9]{4}");


    EditText etname;
    EditText etemail;
    EditText etphone;
    EditText etDOB;
    Spinner spgender;
    EditText etprofession;
    RadioGroup radioGroup;
    EditText etID;
    EditText etpword;
    EditText etrepword;
    EditText etPIN;
    EditText etPresentAddress;
    Spinner spPrDivision;
    Spinner spPrDistrict;
    Spinner spPrThana;
    EditText etPermanentAddress;
    Spinner spPerDivision;
    Spinner spPerDistrict;
    Spinner spPerThana;

    String user_id;
    Button btRegister;

    int flag = 0;

    @SuppressWarnings("deprecation")
    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Calendar c=Calendar.getInstance();
        mYear=c.get(Calendar.YEAR);
        mMonth=c.get(Calendar.MONTH);
        mDay=c.get(Calendar.DAY_OF_MONTH);
        //String dateFormat = "dd/MM/yyyy";
        etDOB = (EditText) findViewById(R.id.uBirth);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        etDOB.setText(sdf.format(c.getTime()));

        etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);
            }
        });


        Intent in = getIntent();
        Bundle b = in.getExtras();

        if (b != null) {
            user_id = (String) b.get("ID");
            // t.setText("Your ID is :" + passed_id_value);
        }



        Log.v("HELLO", "ONCREATE");
        etname = (EditText) findViewById(R.id.et_user_name);
        etpword = (EditText) findViewById(R.id.uPassword);
        etrepword = (EditText) findViewById(R.id.rPassword);
        etemail = (EditText) findViewById(R.id.et_email_address);
        etphone = (EditText) findViewById(R.id.et_phone_number);
        spgender = (Spinner) findViewById(R.id.uGender);
        etprofession = (EditText) findViewById(R.id.uProfession);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        etID = (EditText) findViewById(R.id.uID);
        etPIN = (EditText)findViewById(R.id.uPin);
        etPresentAddress = (EditText)findViewById(R.id.uPrAddress);
        spPrDivision = (Spinner) findViewById(R.id.uPrDivision);
        spPrDistrict = (Spinner) findViewById(R.id.uPrDistrict);
        spPrThana = (Spinner) findViewById(R.id.uPrThana);

        spPrDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                parent.getItemAtPosition(pos);
                if (pos == 0) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Barguna,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 1) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Barisal,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 2) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Bhola,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 3) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Bandarban,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 4) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Brahmanbaria,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 5) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Bagerhat,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 6) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Bogra,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 7) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Chuadanga,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 8) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Chapainawabganj,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 9) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Chandpur,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 10) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Chittagong,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 11) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Coxs_Bazar,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 12) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Comilla,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 13) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Dhaka,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 14) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Dinajpur,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 15) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Feni,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 16) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Faridpur,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 17) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Gazipur,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 18) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Gaibandha,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 19) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Gopalganj,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 20) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Habiganj,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 21) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Jhalokati,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 22) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Jamalpur,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 23) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Joypurhat,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 24) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Jessore,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 25) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Jhenidah,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 26) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Khagrachori,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 27) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Kishoreganj,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 28) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Khulna,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 29) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Kushtia,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 30) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Kurigram,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 31) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Lalmonirhat,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 32) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Lakshmipur,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 33) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Madaripur,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 34) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Manikganj,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 35) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Munshiganj,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 36) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Moulovibazar,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 37) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Magura,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 38) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Meherpur,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 39) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Mymensing,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 40) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Netrakona,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 41) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Narail,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 42) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Narayonganj,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 43) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Narsingdi,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 44) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Noakhali,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 45) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Nilphamari,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 46) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Naogaon,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 47) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Natore,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 48) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Patuakhali,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 49) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Pirojpur,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 50) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Pabna,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 51) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Panchagarh,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 52) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Rangpur,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 53) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Rajshahi,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 54) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Rangamati,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 55) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Rajbari,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 56) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Sherpur,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 57) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Satkhira,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 58) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Sirajganj,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 59) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Sunamganj,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                if (pos == 60) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Sylhet,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 61) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Sylhet,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                } else if (pos == 62) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Shariatpur,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
                else if (pos == 63) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.Thana_Tangail,
                                    android.R.layout.simple_spinner_item);
                    spPrThana.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        etPermanentAddress = (EditText)findViewById(R.id.uPAddress);
        spPerDivision = (Spinner) findViewById(R.id.uDivision);
        spPerDistrict = (Spinner) findViewById(R.id.uDistrict);
        spPerThana = (Spinner) findViewById(R.id.uThana);

        spPerDistrict.setOnItemSelectedListener(this);

        btRegister = (Button) findViewById(R.id.btn_submit);





        btRegister.setOnClickListener(this);
    }

    public void RadioButtonClicked(View view) {



// Check that the button is  now checked?
        boolean checked = ((RadioButton) view).isChecked();

// Check which radio button was clicked
        switch (view.getId()) {
            case R.id.uNid:
                if (checked)
                    selectedID = "n ";
                Toast.makeText(Registration.this, "NID", Toast.LENGTH_LONG).show();
                //etID.setText("n ");
                Selection.setSelection(etID.getText(), etID.getText().length());
                break;

            case R.id.uPassport:
                if (checked)
                    selectedID = "p ";
                Toast.makeText(Registration.this, "Passport", Toast.LENGTH_LONG).show();
                // etID.setText("Passport");
                // etID.setText("p ");
                Selection.setSelection(etID.getText(), etID.getText().length());

                break;

            case R.id.uLicense:
                if (checked)
                    selectedID = "d ";
                Toast.makeText(Registration.this, "Driving License", Toast.LENGTH_LONG).show();
                Selection.setSelection(etID.getText(), etID.getText().length());

                break;
        }
    }

    private void signup() {
        final String name = etname.getText().toString().trim();
        final String password = etpword.getText().toString().trim();
        final String repassword = etrepword.getText().toString().trim();
        final String email = etemail.getText().toString().trim();
        final String phoneNumber = etphone.getText().toString().trim();
        final String dob = etDOB.getText().toString().trim();
        final String Gender = spgender.getSelectedItem().toString().trim();
        final String prof = etprofession.getText().toString().trim();
        final String id = etID.getText().toString().trim();
        // final String radiovalue =  ((RadioButton)this.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
        final String pin = etPIN.getText().toString().trim();
        final String presentAddress = etPresentAddress.getText().toString().trim();
        final String prDivision = spPrDivision.getSelectedItem().toString().trim();
        final String prDistrict = spPrDistrict.getSelectedItem().toString().trim();
        final String prThana = spPrThana.getSelectedItem().toString().trim();
        final String permanentAddress = etPermanentAddress.getText().toString().trim();
        final String perDivision = spPerDivision.getSelectedItem().toString().trim();
        final String perDistrict = spPerDistrict.getSelectedItem().toString().trim();
        final String perThana = spPerThana.getSelectedItem().toString().trim();



        if (name.equals("")) {
            Toast.makeText(Registration.this, "Name can not be empty",
                    Toast.LENGTH_LONG).show();

        } else if (!CheckUsername(name)) {
            Toast.makeText(Registration.this, "Only letters and white space allowed",
                    Toast.LENGTH_LONG).show();
        } else if (email.equals("")) {
            Toast.makeText(Registration.this, "Email can not be empty",
                    Toast.LENGTH_LONG).show();

        }else if (!CheckEmail(email)) {
            Toast.makeText(Registration.this, "Enter a valid email",
                    Toast.LENGTH_LONG).show();
        } else if (phoneNumber.equals("")) {
            Toast.makeText(Registration.this, "Phone can not be empty",
                    Toast.LENGTH_LONG).show();

        }else if (!CheckPhoneNumber(phoneNumber)) {
            Toast.makeText(Registration.this, "Enter a valid phone number",
                    Toast.LENGTH_LONG).show();
        } else if (dob.equals("")) {
            Toast.makeText(Registration.this, "Date of birth can not be empty",
                    Toast.LENGTH_LONG).show();

        }else if (Gender.equals("")) {
            Toast.makeText(Registration.this, "Select your gender",
                    Toast.LENGTH_LONG).show();

        }else if (prof.equals("")) {
            Toast.makeText(Registration.this, "Profession can not be empty",
                    Toast.LENGTH_LONG).show();

        }else if (id.equals("")) {
            Toast.makeText(Registration.this, "ID can not be empty",
                    Toast.LENGTH_LONG).show();

        }else if (!CheckIDNumber(id)) {
            Toast.makeText(Registration.this, "Enter a valid id",
                    Toast.LENGTH_LONG).show();
        } else if (password.equals("")) {
            Toast.makeText(Registration.this, "Password can not be empty",
                    Toast.LENGTH_LONG).show();

        } else if (!CheckPassword(password)) {
            Toast.makeText(Registration.this, "Password should be at least 6 character. No space allowed",
                    Toast.LENGTH_LONG).show();

        } else if (repassword.equals("")) {
            Toast.makeText(Registration.this, "Re password can not be empty",
                    Toast.LENGTH_LONG).show();

        } else if (!repassword.equals(password)) {
            Toast.makeText(Registration.this, "Password did not match",
                    Toast.LENGTH_LONG).show();

        } else if (pin.equals("")) {
            Toast.makeText(Registration.this, "Pin can not be empty",
                    Toast.LENGTH_LONG).show();

        } else if (!CheckPINNumber(pin)) {
            Toast.makeText(Registration.this, "Use number as pin [0-9] and pin limit is 4",
                    Toast.LENGTH_LONG).show();

        } else if (presentAddress.equals("")) {
            Toast.makeText(Registration.this, "Present Address can not be empty",
                    Toast.LENGTH_LONG).show();

        } else if (prDivision.equals("")) {
            Toast.makeText(Registration.this, "Select Division for present address",
                    Toast.LENGTH_LONG).show();

        } else if (prDistrict.equals("")) {
            Toast.makeText(Registration.this, "Select District for present address",
                    Toast.LENGTH_LONG).show();

        } else if (prThana.equals("")) {
            Toast.makeText(Registration.this, "Select Thana for present address",
                    Toast.LENGTH_LONG).show();

        }else if (permanentAddress.equals("")) {
            Toast.makeText(Registration.this, "Permanent Address can not be empty",
                    Toast.LENGTH_LONG).show();

        } else if (perDivision.equals("")) {
            Toast.makeText(Registration.this, "Select Division for permanent address",
                    Toast.LENGTH_LONG).show();

        } else if (perDistrict.equals("")) {
            Toast.makeText(Registration.this, "Select District for permanent address",
                    Toast.LENGTH_LONG).show();

        } else if (perThana.equals("")) {
            Toast.makeText(Registration.this, "Select Thana for permanent address",
                    Toast.LENGTH_LONG).show();

        }   else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Registration.this,response,Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Registration.this,error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String, String>();
                    params.put(Config.KEY_NAME, name);
                    params.put(Config.KEY_EMAIL,email);
                    params.put(Config.KEY_PHONE, phoneNumber);
                    params.put(Config.KEY_DOB,dob);
                    params.put(Config.KEY_GENDER,Gender);
                    params.put(Config.KEY_PROFESSION, prof);
                    params.put(Config.KEY_ID,selectedID+id);
                    params.put(Config.KEY_PASSWORD,password);
                    // params.put(Config.KEY_REPASSWORD, repassword);
                    params.put(Config.KEY_PIN,pin);
                    params.put(Config.KEY_PRESENTADDRESS,presentAddress);
                    params.put(Config.KEY_PRESENTDIVISION, prDivision);
                    params.put(Config.KEY_PRESENTDISTRICT,prDistrict);
                    params.put(Config.KEY_PRESENTTHANA,prThana);
                    params.put(Config.KEY_PERMANENTADDRESS,permanentAddress);
                    params.put(Config.KEY_PERMANENTDIVISION,perDivision);
                    params.put(Config.KEY_PERMANENTDISTRICT, perDistrict);
                    params.put(Config.KEY_PERMANENTTHANA, perThana);
                    params.put(Config.KEY_USERID,user_id);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


            Intent i = new Intent(Registration.this, Login.class);
            startActivity(i);
            //Toast.makeText(Registration.this, "Registration Successful",
            //   Toast.LENGTH_LONG).show();

        }



    }





    private boolean CheckEmail(String email) {

        return EMAIL_PATTERN.matcher(email).matches();
    }
    private boolean CheckPassword(String password) {

        return PASSWORD_PATTERN.matcher(password).matches();
    }

    private boolean CheckUsername(String username) {

        return NAME_PATTERN.matcher(username).matches();
    }
    private boolean CheckPhoneNumber(String phoneNumber) {

        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }

    private boolean CheckIDNumber(String id) {

        return ID_PATTERN.matcher(id).matches();
    }
    private boolean CheckPINNumber(String pin) {

        return PIN_PATTERN.matcher(pin).matches();
    }



    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);

        }
        return null;

    }
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            etDOB.setText(new StringBuilder().append(mDay).append("/").append(mMonth + 1).append("/").append(mYear));

        }

    };

    @Override
    public void onClick(View v) {
        if(v == btRegister){
            signup();

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        parent.getItemAtPosition(pos);
        if (pos == 0) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Barguna,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 1) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Barisal,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 2) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Bhola,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 3) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Bandarban,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 4) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Brahmanbaria,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 5) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Bagerhat,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 6) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Bogra,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 7) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Chuadanga,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 8) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Chapainawabganj,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 9) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Chandpur,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 10) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Chittagong,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 11) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Coxs_Bazar,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 12) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Comilla,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 13) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Dhaka,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 14) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Dinajpur,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 15) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Feni,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 16) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Faridpur,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 17) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Gazipur,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 18) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Gaibandha,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 19) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Gopalganj,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 20) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Habiganj,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 21) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Jhalokati,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 22) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Jamalpur,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 23) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Joypurhat,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 24) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Jessore,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 25) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Jhenidah,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 26) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Khagrachori,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 27) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Kishoreganj,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 28) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Khulna,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 29) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Kushtia,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 30) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Kurigram,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 31) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Lalmonirhat,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 32) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Lakshmipur,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 33) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Madaripur,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 34) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Manikganj,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 35) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Munshiganj,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 36) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Moulovibazar,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 37) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Magura,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 38) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Meherpur,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 39) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Mymensing,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 40) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Netrakona,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 41) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Narail,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 42) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Narayonganj,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 43) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Narsingdi,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 44) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Noakhali,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 45) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Nilphamari,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 46) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Naogaon,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 47) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Natore,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 48) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Patuakhali,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 49) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Pirojpur,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 50) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Pabna,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 51) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Panchagarh,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 52) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Rangpur,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 53) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Rajshahi,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 54) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Rangamati,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 55) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Rajbari,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 56) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Sherpur,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 57) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Satkhira,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 58) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Sirajganj,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 59) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Sunamganj,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        if (pos == 60) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Sylhet,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 61) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Sylhet,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        } else if (pos == 62) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Shariatpur,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
        else if (pos == 63) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.Thana_Tangail,
                            android.R.layout.simple_spinner_item);
            spPerThana.setAdapter(adapter);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}