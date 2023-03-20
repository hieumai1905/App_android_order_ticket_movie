package com.example.btl_android_apporderticket.DAO.database;

import java.util.List;

public interface IHandlerDatabase<T, K> {
    void add(T t);
    T getById(K id);
    List<T> getAll();
    void update(T t);
    void remove(K id);
}