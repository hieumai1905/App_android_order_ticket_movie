package com.example.btl_android_apporderticket.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.btl_android_apporderticket.R;

public class LoginActivity extends Activity {
    private TextView closeUILogin;
    private TextView forgotPassword;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initApp();
        setEvent();


    }

    private void initApp() {
        closeUILogin = findViewById(R.id.tvExitLogin);
        forgotPassword = findViewById(R.id.tvForgotPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRequestRegister);
    }

    private void setEvent() {
    }
}
