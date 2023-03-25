package com.example.btl_android_apporderticket.service.invoice;

import androidx.annotation.NonNull;

import com.example.btl_android_apporderticket.api.InvoiceAPI;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Invoice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceService implements IInvoiceService {
    private static IInvoiceService instance;

    private InvoiceService() {
    }

    public static IInvoiceService getInstanceInvoiceService() {
        if (instance == null) {
            instance = new InvoiceService();
        }
        return instance;
    }

    @Override
    public void getById(String key, IServiceCallback<Invoice> callback) {

    }

    @Override
    public void getAll(IServiceCallback<Iterable<Invoice>> callback) {

    }

    @Override
    public void add(Invoice entity, IServiceCallback<Invoice> callback) {
        InvoiceAPI.invoiceAPI.paymentTicket(entity).enqueue(new Callback<Invoice>() {
            @Override
            public void onResponse(@NonNull Call<Invoice> call, @NonNull Response<Invoice> response) {
                if (response.isSuccessful()) {
                    callback.onDataReceived(response.body());
                } else {
                    callback.onRequestFailed(new Exception("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Invoice> call, Throwable t) {
                callback.onRequestFailed(t);
            }
        });
    }

    @Override
    public void update(String key, Invoice entity, IServiceCallback<Boolean> callback) {

    }

    @Override
    public void remove(String key, IServiceCallback<Boolean> callback) {

    }
}
