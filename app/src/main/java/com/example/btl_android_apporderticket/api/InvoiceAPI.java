package com.example.btl_android_apporderticket.api;

import com.example.btl_android_apporderticket.config.Configuration;
import com.example.btl_android_apporderticket.model.Invoice;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InvoiceAPI {
    InvoiceAPI invoiceAPI = new Retrofit.Builder().baseUrl(Configuration.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create(Configuration.gson)).build().create(InvoiceAPI.class);

    @POST("invoices")
    Call<Invoice> paymentTicket(@Body Invoice invoice);

    @GET("invoices/users/{userId}")
    Call<List<Invoice>> getInvoiceByUserId(@Path("userId") String userId);
}
