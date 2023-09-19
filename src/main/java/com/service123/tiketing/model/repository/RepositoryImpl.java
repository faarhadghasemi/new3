package com.service123.tiketing.model.repository;

import java.util.List;

public interface RepositoryImpl<T> extends AutoCloseable {
    T save(T t);
    T edit(T t);
    T remove(long id);
    List<T> findAll();
    T findById(long id);
}
