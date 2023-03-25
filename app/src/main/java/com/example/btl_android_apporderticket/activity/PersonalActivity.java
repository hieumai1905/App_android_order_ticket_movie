package com.example.btl_android_apporderticket.activity;


import static com.example.btl_android_apporderticket.config.Configuration.LOGOUT_CODE;
import static com.example.btl_android_apporderticket.config.Configuration.UPDATE_ACCOUNT_RESULT_CODE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.User;
import com.example.btl_android_apporderticket.service.user.IUserService;
import com.example.btl_android_apporderticket.service.user.UserService;

public class PersonalActivity extends Activity {

    private TextView tvUpdateProfile;
    private TextView tvChangePassword;
    private TextView tvClosePersonal;
    private TextView tvRemoveUser;
    private TextView tvLogout;
    private TextView tvHistory;
    private TextView tvUserName;
    private User UserCurrent;

    private IUserService userService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        getData();
        initApp();
        setEvent();
    }

    private void getData() {
        Intent intent = getIntent();
        UserCurrent = (User) intent.getSerializableExtra("userCurrent");
    }


    private void initApp() {
        userService = UserService.getInstanceUserService();
        tvUpdateProfile = findViewById(R.id.tvUpdateProfile);
        tvChangePassword = findViewById(R.id.tvChangePassword);
        tvClosePersonal = findViewById(R.id.tvExitPersonal);
        tvRemoveUser = findViewById(R.id.tvRemovePersonal);
        tvLogout = findViewById(R.id.tvLogOut);
        tvHistory = findViewById(R.id.tvPaymentHistory);
        tvUserName = findViewById(R.id.tvUserName);
        tvUserName.setText(UserCurrent.getFullname().toUpperCase());
    }

    private void setEvent() {
        tvClosePersonal.setOnClickListener(v -> {
            Intent intent = new Intent(PersonalActivity.this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("userCurrent", UserCurrent);
            intent.putExtras(bundle);
            setResult(UPDATE_ACCOUNT_RESULT_CODE, intent);
            finish();
        });

        tvLogout.setOnClickListener(v -> {
            Intent intent = new Intent(PersonalActivity.this, MainActivity.class);
            setResult(LOGOUT_CODE, intent);
            finish();
        });

        tvChangePassword.setOnClickListener(v -> {
            showDialogChangePassword();
        });
    }

    private void showDialogChangePassword() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_change_password);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams windowAttributes = window.getAttributes();
            windowAttributes.gravity = Gravity.BOTTOM;
            window.setAttributes(windowAttributes);
            dialog.setCancelable(true);
            setEventDiaLog(dialog);
            dialog.show();
        }
    }

    private void setEventDiaLog(Dialog dialog) {
        TextView tvCancel = dialog.findViewById(R.id.tvCancelChangePassword);
        EditText edtPasswordCurrent = dialog.findViewById(R.id.edtPasswordCurrent);
        EditText edtPassword = dialog.findViewById(R.id.edtPasswordNew);
        EditText edtConfirmPassword = dialog.findViewById(R.id.edtPasswordConfirmNew);

        tvCancel.setOnClickListener(v -> {
            dialog.dismiss();
        });

        edtConfirmPassword.setOnClickListener(v -> {

            String passwordCurrent = edtPasswordCurrent.getText().toString();
            String password = edtPassword.getText().toString();
            String confirmPassword = edtConfirmPassword.getText().toString();

            if (passwordCurrent.isEmpty()) {
                edtPasswordCurrent.setError("Password current is empty");
                return;
            }

            if (password.isEmpty()) {
                edtPassword.setError("Password is empty");
                return;
            }

            if (confirmPassword.isEmpty()) {
                edtConfirmPassword.setError("Confirm password is empty");
                return;
            }

            if (passwordCurrent.equals(UserCurrent.getPassword())) {
                if (password.equals(confirmPassword)) {
                    UserCurrent.setPassword(password);
                    userService.update(UserCurrent.getPassword(), UserCurrent, new IServiceCallback<Boolean>() {
                        @Override
                        public void onDataReceived(Boolean data) {
                            if (data) {
                                System.out.println("-----------------------Change password success-----------------------");
                                dialog.dismiss();

                                Intent intent = new Intent(PersonalActivity.this, MainActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("userCurrent", UserCurrent);
                                intent.putExtras(bundle);
                                setResult(UPDATE_ACCOUNT_RESULT_CODE, intent);
                                finish();
                            }
                        }

                        @Override
                        public void onRequestFailed(Throwable t) {
                            System.out.println("-----------------------Change password failed-----------------------");
                            System.out.println(t.getMessage());
                        }
                    });
                } else {
                    System.out.println("-----------------------Password not match-----------------------");
                    edtConfirmPassword.setError("Password not match");
                    edtConfirmPassword.requestFocus();
                }
            } else {
                System.out.println("-----------------------Password current not correct-----------------------");
                edtPasswordCurrent.setError("Password current not correct");
                edtPasswordCurrent.requestFocus();
                edtPasswordCurrent.setText("");
            }
            edtPassword.setText("");
            edtConfirmPassword.setText("");
        });

    }
}
