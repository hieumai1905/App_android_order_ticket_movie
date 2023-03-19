package com.example.btl_android_apporderticket.service;


import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;

public interface IGeneralService<T, K> {
    void getById(K key, IServiceCallback<T> callback);

    void getAll(IServiceCallback<Iterable<T>> callback);

    void add(T entity, IServiceCallback<T> callback);

    void update(K key, T entity, IServiceCallback<Boolean> callback);

    void remove(K key, IServiceCallback<Boolean> callback);
}
