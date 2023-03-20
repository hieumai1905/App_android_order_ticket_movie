package com.example.btl_android_apporderticket.api;


import com.example.btl_android_apporderticket.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserAPI {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

    UserAPI userAPI = new Retrofit.Builder().baseUrl("http://172.20.10.2:7221/api/").addConverterFactory(GsonConverterFactory.create(gson)).build().create(UserAPI.class);

    @GET("users")
    Call<List<User>> getAllUsers();

    @GET("users/{userId}")
    Call<User> getUserById(@Path("userId") String userId);

    @POST("users/register")
    Call<User> createUser(@Body User user);

    @PUT("users")
    Call<User> updateUser(@Query("id") String userId, @Body User user);

    @DELETE("users/{userId}")
    Call<Void> deleteUser(@Path("userId") String userId);

    @POST("users/login")
    Call<User> loginUser(@Body User user);
}
