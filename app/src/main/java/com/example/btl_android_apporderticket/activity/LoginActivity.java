package com.example.btl_android_apporderticket.activity;

import static com.example.btl_android_apporderticket.define.RequestCode.LOGIN_RESULT_CODE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;

import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.handle.system.HandleKeyBoard;
import com.example.btl_android_apporderticket.model.User;
import com.example.btl_android_apporderticket.service.user.UserService;

public class LoginActivity extends Activity {
    private TextView closeUILogin;
    private TextView forgotPassword;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnRegister;

    private UserService userService;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initApp();
        setEvent();
    }

    private void initApp() {

        userService = UserService.getInstanceUserService();
        closeUILogin = findViewById(R.id.tvExitLogin);
        forgotPassword = findViewById(R.id.tvForgotPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRequestRegister);
    }

    private void setEvent() {
        closeUILogin.setOnClickListener(v -> {
            finish();
        });

        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        btnLogin.setOnClickListener(v -> {
            HandleKeyBoard.hideKeyboard(v, this);
            if (checkInput()) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                loginUser(new User(email, password));
            } else {
                // thong bao nhap thieu
            }
        });
    }

    private void loginUser(User user) {

        try {
            userService.login(user, new IServiceCallback<User>() {
                @Override
                public void onDataReceived(User data) {
                    System.out.println(data.toString());
                    System.out.println("-------------Login success-------------------");
                    clearInput();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("user", data);
                    intent.putExtras(bundle);
                    setResult(LOGIN_RESULT_CODE, intent);
                    finish();
                }

                @Override
                public void onRequestFailed(Throwable t) {
                    System.out.println("---------------Login failed-------------------");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearInput() {
        edtEmail.setText("");
        edtPassword.setText("");
    }

    private boolean checkInput() {
        if (edtEmail.getText().toString().isEmpty()) {
            edtEmail.setError("Vui lòng nhập email");
            edtEmail.setFocusable(true);
            return false;
        }
        if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setError("Vui lòng nhập password");
            edtPassword.setFocusable(true);
            return false;
        }
        return true;
    }

}
