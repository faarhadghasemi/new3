package com.service123.tiketing.model;

import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.service123.tiketing.model.entity.User;
import com.service123.tiketing.model.service.UserService;

public class Test {
    public static void main(String[] args) throws Exception {
        User user = User.builder().name("ahmad").family("mesbah").userName("ahmad").password("ahmad123").build();
        System.out.println(UserService.getService().save(user));
    }
}
