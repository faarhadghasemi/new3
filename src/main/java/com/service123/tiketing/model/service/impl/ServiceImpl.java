package com.service123.tiketing.model.service.impl;

import com.service123.tiketing.model.entity.UserDto;

import java.util.List;

public interface ServiceImpl<T> {
    T save(T t) throws Exception;
    T edit(T t)  throws Exception ;
    T remove(long id)  throws Exception ;
    List<T> findAll()  throws Exception ;
    T findById(long id)  throws Exception ;

}
