package com.sunking.it.moneybag;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mohita on 3/29/16.
 */
public class Config {

    public static String ip ="192.168.0.100";
    public static TextView curBalance;
    public static final String LOGIN_URL = "http://" + ip + "/moneybag/Login.php";
    public static final String FORGOTPASSWORD_URL = "http://" + ip + "/moneybag/forgotPassword.php";
    public static final String REGISTER_URL = "http://" + ip + "/moneybag/Register.php";
    public static final String PROFILE_URL = "http://"+ ip + "/moneybag/getdata.php";
    public static final String CHANGEPIN_URL = "http://"+ ip + "/moneybag/changepin.php";
    public static final String SENDMONEY_URL = "http://"+ ip + "/moneybag/sendMoney.php";//?Email=";
    public static final String CHANGEPASS_URL = "http://" +ip + "/moneybag/changepass.php";//?Email=";
    public static final String THISTORY_URL = "http://" +ip + "/moneybag/getTransactionHistory.php";
    public static final String RECEIVEDMONEY_URL ="http://" +ip + "/moneybag/getReceivedMoney.php";
    public static final String CASHOUT_URL = "http://" +ip + "/moneybag/cashout.php";
    public static final String EDITPROFILE_URL = "http://" +ip + "/moneybag/editProfile.php";

    public static final String SHARED_PREF_NAME = "myloginapp";
    public static final String EMAIL_SHARED_PREF = "Myval";

    public static final String PASSWORD_SHARED_PREF = "password";
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";


    public static final String KEY_NAME = "Name";
    public static final String KEY_NEWNAME = "newName";
    public static final String KEY_EMAIL = "Email";
    public static final String KEY_PHONE = "Phone";
    public static final String KEY_DOB = "DOB";
    public static final String KEY_GENDER = "Gender";
    public static final String KEY_PROFESSION = "Profession";
    public static final String KEY_NEWPROFESSION = "newProf";
    public static final String KEY_ID = "N_ID";
    public static final String KEY_PASSWORD = "Password";
    public static final String KEY_NEWPASSWORD = "newPassword";
    // public static final String KEY_REPASSWORD = "Re_password";
    public static final String KEY_PIN = "Pin";
    public static final String KEY_NEWPIN = "newPin";
    public static final String KEY_PRESENTADDRESS = "Present_Address";
    public static final String KEY_NEWPRESENTADDRESS = "newPrAdd";
    public static final String KEY_PRESENTDIVISION = "Present_Division";
    public static final String KEY_PRESENTDISTRICT = "Present_District";
    public static final String KEY_PRESENTTHANA = "Present_Thana";
    public static final String KEY_PERMANENTADDRESS = "Permanent_Address";
    public static final String KEY_PERMANENTDIVISION = "Permanent_Division";
    public static final String KEY_PERMANENTDISTRICT = "Permanent_District";
    public static final String KEY_PERMANENTTHANA = "Permanent_Thana";
    public static final String KEY_USERID = "User_ID";

    public static final String KEY_RECEIVERID = "ReceiverID";
    //  public static final String KEY_NEWBALANCE = "newbalance";
    public static final String KEY_SAMOUNT = "SendAmount";
    public static final ArrayList<String> userInfo = new ArrayList<String>();



}