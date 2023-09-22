package com.service123.tiketing.model.service;

import com.service123.tiketing.model.entity.User;
import com.service123.tiketing.model.repository.UserRepository;
import com.service123.tiketing.model.service.impl.ServiceImpl;

import java.util.List;

public class UserService implements ServiceImpl<User> {
    private static UserService userService=new UserService();

    private UserService() {
    }

    public static UserService getService() {
        return userService;
    }

    @Override
    public User save(User user) throws Exception {
        try(UserRepository repository = new UserRepository()){
            return repository.save(user);
        }
    }

    @Override
    public User edit(User user) throws Exception {
        try (UserRepository repository=new UserRepository()){
            return  repository.edit(user);
        }
    }

    @Override
    public User remove(long id) throws Exception {
        try(UserRepository repository = new UserRepository()){
            return repository.remove(id);
        }
    }

    @Override
    public List<User> findAll() throws Exception {
        try(UserRepository repository = new UserRepository()){
            return repository.findAll();
        }
    }

    @Override
    public User findById(long id) throws Exception {
        try(UserRepository repository = new UserRepository()){
            return repository.findById(id);
        }
    }
}
