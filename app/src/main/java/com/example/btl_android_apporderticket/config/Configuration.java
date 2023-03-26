package com.example.btl_android_apporderticket.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Configuration {
    public static final int START_APP_CODE = 50000;
    public static final int LOGIN_REQUEST_CODE = 50001;
    public static final int LOGIN_RESULT_CODE = 50002;
    public static final int LOGOUT_CODE = 50003;
    public static final int UPDATE_ACCOUNT_RESULT_CODE = 50004;

    public static final String URL_BASE = "http://192.168.99.242:8080/api/";
    public static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

}
