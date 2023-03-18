package com.example.btl_android_apporderticket.service.user;

import androidx.annotation.NonNull;

import com.example.btl_android_apporderticket.api.UserAPI;
import com.example.btl_android_apporderticket.handle.IServiceCallback;
import com.example.btl_android_apporderticket.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService implements IUserService {

    public UserService() {
    }

    @Override
    public void getById(String key, IServiceCallback<User> callback) {
        UserAPI.userAPI.getUserById(key).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    callback.onDataReceived(response.body());
                } else {
                    callback.onRequestFailed(new Exception("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, Throwable t) {
                callback.onRequestFailed(t);
            }
        });
    }

    @Override
    public void getAll(IServiceCallback<Iterable<User>> callback) {
        UserAPI.userAPI.getAllUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if (response.isSuccessful()) {
                    Iterable<User> users = response.body();
                    callback.onDataReceived(users);
                } else {
                    callback.onRequestFailed(new Exception("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, Throwable t) {
                callback.onRequestFailed(t);
            }
        });
    }


    @Override
    public void add(User entity, IServiceCallback<User> callback) {
        UserAPI.userAPI.createUser(entity).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    callback.onDataReceived(response.body());
                } else {
                    callback.onRequestFailed(new Exception("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, Throwable t) {
                callback.onRequestFailed(t);
            }
        });
    }

    @Override
    public void update(String key, User entity, IServiceCallback<Boolean> callback) {
        UserAPI.userAPI.updateUser(key, entity).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    callback.onDataReceived(true);
                } else {
                    callback.onRequestFailed(new Exception("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, Throwable t) {
                callback.onRequestFailed(t);
            }
        });
    }

    @Override
    public void remove(String key, IServiceCallback<Boolean> callback) {

        UserAPI.userAPI.deleteUser(key).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onDataReceived(true);
                } else {
                    callback.onRequestFailed(new Exception("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, Throwable t) {
                callback.onRequestFailed(t);
            }
        });
    }

    @Override
    public void login(User user, IServiceCallback<User> callback) {
        UserAPI.userAPI.loginUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    callback.onDataReceived(response.body());
                } else {
                    callback.onRequestFailed(new Exception("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, Throwable t) {
                callback.onRequestFailed(t);
            }
        });
    }
}
