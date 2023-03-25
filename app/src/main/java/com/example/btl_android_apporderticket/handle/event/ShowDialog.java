package com.example.btl_android_apporderticket.handle.event;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.handle.mycallback.ICallbackEventClick;

import org.w3c.dom.Text;

public class ShowDialog {
    public static void show(Activity activity, String title, String message, String button, String sub, ICallbackEventClick callbackEventClick) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AlertDialog);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_success, activity.findViewById(R.id.ll_dialog_success));
        builder.setView(view);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvMessage = view.findViewById(R.id.tvMessage);
        Button btnOk = view.findViewById(R.id.btnOK);
        TextView tvSub = view.findViewById(R.id.tvSubMessage);
        tvTitle.setText(title);
        tvMessage.setText(message);
        btnOk.setText(button);
        tvSub.setText(sub);
        AlertDialog alertDialog = builder.show();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                callbackEventClick.onSelectObject(null);
            }
        });
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
}
