package com.example.btl_android_apporderticket.api;

import com.example.btl_android_apporderticket.config.Configuration;
import com.example.btl_android_apporderticket.model.Genre;
import com.example.btl_android_apporderticket.model.Invoice;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InvoiceAPI {
    InvoiceAPI invoiceAPI = new Retrofit.Builder().baseUrl(Configuration.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create(Configuration.gson)).build().create(InvoiceAPI.class);

    @POST("invoices")
    Call<Invoice> paymentTicket(@Body Invoice invoice);
}
