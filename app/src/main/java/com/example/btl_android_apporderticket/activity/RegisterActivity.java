package com.example.btl_android_apporderticket.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.User;
import com.example.btl_android_apporderticket.service.user.IUserService;
import com.example.btl_android_apporderticket.service.user.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegisterActivity extends Activity {
    private TextView tvExitRegister;
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private EditText edtPhone;
    private EditText edtAddress;
    private EditText edtBirthday;
    private CheckBox ckbRegister;
    private Spinner spinner;
    private Button btnRegister;

    private IUserService userService;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initApp();
        setEvent();
    }

    private void initApp() {
        userService = UserService.getInstanceUserService();
        edtName = findViewById(R.id.edtRegisterName);
        tvExitRegister = findViewById(R.id.tvExitRegister);
        edtEmail = findViewById(R.id.edtRegisterEmail);
        edtPassword = findViewById(R.id.edtRegisterPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        edtPhone = findViewById(R.id.edtRegisterPhone);
        edtAddress = findViewById(R.id.edtRegisterAddress);
        edtBirthday = findViewById(R.id.edtRegisterBirthday);
        ckbRegister = findViewById(R.id.ckbRegister);
        spinner = findViewById(R.id.spnGender);
        btnRegister = findViewById(R.id.btnConfirmRegister);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setEvent() {
        tvExitRegister.setOnClickListener(v -> {
            finish();
        });
        ckbRegister.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                btnRegister.setEnabled(true);
            } else {
                btnRegister.setEnabled(false);
            }
        });
        btnRegister.setOnClickListener(v -> {
            if (checkInput()) {
                if (checkPassword()) {
                    registerUser();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void registerUser() {
        String name = edtName.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String phone = edtPhone.getText().toString();
        String address = edtAddress.getText().toString();
        String gender = spinner.getSelectedItem().toString();
        String age = edtBirthday.getText().toString();
        LocalDateTime birth = null;
        birth = LocalDateTime.now();
        LocalDateTime eighteenYearsAgo = birth.minusYears(Integer.parseInt(age));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String date = eighteenYearsAgo.format(formatter);
        System.out.println(birth);
        User user = new User(name, phone, email, password, gender, address, date);
        try {
            userService.add(user, new IServiceCallback<User>() {
                @Override
                public void onDataReceived(User data) {
                    System.out.println("---------------------Register success---------------------");
                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onRequestFailed(Throwable t) {
                    System.out.println("---------------------Register failed---------------------");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkPassword() {
        if ((edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString()))) {
            return true;
        } else {
            edtPassword.setText("");
            edtConfirmPassword.setText("");
            edtPassword.setError("Mật khẩu không khớp");
            edtConfirmPassword.setError("Mật khẩu không khớp");
        }
        return false;
    }

    private boolean checkInput() {
        if (edtName.getText().toString().isEmpty()) {
            edtName.setError("Vui lòng nhập tên");
            edtName.setFocusable(true);
            return false;
        }
        if (edtEmail.getText().toString().isEmpty()) {
            edtEmail.setError("Vui lòng nhập email");
            edtName.setFocusable(true);
            return false;
        }
        if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setError("Vui lòng nhập mật khẩu");
            edtPassword.setFocusable(true);
            return false;
        }
        if (edtConfirmPassword.getText().toString().isEmpty()) {
            edtConfirmPassword.setError("Vui lòng nhập lại mật khẩu");
            edtConfirmPassword.setFocusable(true);
            return false;
        }
        if (edtPhone.getText().toString().isEmpty()) {
            edtPhone.setError("Vui lòng nhập số điện thoại");
            edtPhone.setFocusable(true);
            return false;
        }
        if (edtAddress.getText().toString().isEmpty()) {
            edtAddress.setError("Vui lòng nhập địa chỉ");
            edtAddress.setFocusable(true);
            return false;
        }
        if (edtBirthday.getText().toString().isEmpty()) {
            edtBirthday.setError("Vui lòng nhập tuổi");
            edtBirthday.setFocusable(true);
            return false;
        }
        return true;
    }
}
