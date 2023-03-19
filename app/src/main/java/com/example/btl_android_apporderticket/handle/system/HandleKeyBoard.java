package com.example.btl_android_apporderticket.handle.system;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class HandleKeyBoard {
    public static void hideKeyboard(View v, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
