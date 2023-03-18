package com.example.btl_android_apporderticket.service.user;


import com.example.btl_android_apporderticket.handle.IServiceCallback;
import com.example.btl_android_apporderticket.model.User;
import com.example.btl_android_apporderticket.service.IGeneralService;

public interface IUserService extends IGeneralService<User, String> {
    void login(User user, IServiceCallback<User> callback);
}
