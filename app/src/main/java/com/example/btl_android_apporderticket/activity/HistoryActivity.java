package com.example.btl_android_apporderticket.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.adapter.InvoiceAdapter;
import com.example.btl_android_apporderticket.handle.getdata.DataBuffer;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Invoice;
import com.example.btl_android_apporderticket.service.invoice.IInvoiceService;
import com.example.btl_android_apporderticket.service.invoice.InvoiceService;

import java.util.List;

public class HistoryActivity extends Activity {
    private TextView tvBack;
    private ListView lvItemHistory;
    private List<Invoice> listInvoice;
    private IInvoiceService invoiceService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initApp();
        setEvent();
        loadData();
    }

    private void initApp() {
        invoiceService = InvoiceService.getInstanceInvoiceService();
        tvBack = findViewById(R.id.tvBack);
        lvItemHistory = findViewById(R.id.lvHistory);
    }

    private void setEvent() {
        tvBack.setOnClickListener(v -> finish());
    }

    private void loadData() {
        invoiceService.getInvoiceByUserId(DataBuffer.userCurrent.getUserId(), new IServiceCallback<Iterable<Invoice>>() {
            @Override
            public void onDataReceived(Iterable<Invoice> data) {
                listInvoice = (List<Invoice>) data;
                InvoiceAdapter historyAdapter = new InvoiceAdapter(listInvoice);
                lvItemHistory.setAdapter(historyAdapter);
            }

            @Override
            public void onRequestFailed(Throwable throwable) {

            }
        });
    }
}
