package com.example.btl_android_apporderticket.handle.mycallback;

public interface IServiceCallback<T> {
    void onDataReceived(T data);

    void onRequestFailed(Throwable t);
}
