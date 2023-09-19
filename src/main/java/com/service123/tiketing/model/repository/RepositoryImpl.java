package com.service123.tiketing.model.repository;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryImpl<T> extends AutoCloseable {
    T save(T t) throws Exception;
    T edit(T t) throws Exception;
    T remove(long id) throws Exception;
    List<T> findAll() throws Exception;
    T findById(long id) throws Exception;
}
