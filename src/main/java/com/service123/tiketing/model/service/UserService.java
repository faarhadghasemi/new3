package com.service123.tiketing.model.service;

import com.service123.tiketing.controller.exception.DuplicateUserNameException;
import com.service123.tiketing.model.entity.User;
import com.service123.tiketing.model.repository.UserRepository;
import com.service123.tiketing.model.service.impl.ServiceImpl;

import java.util.List;

public class UserService implements ServiceImpl<User> {
    private static UserService userService = new UserService();

    private UserService() {
    }

    public static UserService getService() {
        return userService;
    }

//    @Override
//    public User save(User user) throws Exception {
//        try(UserRepository repository = new UserRepository()){
//            if (! repository.isDuplicated(user.getUserName())) {
//                return repository.save(user);
////            }else {
//                //todo: throw exception
////                System.out.println("Duplicated UserName");
////                return null;
//            }
//        }
////    }

    @Override
    public User save(User user) throws Exception {
        try
                (UserRepository userRepository = new UserRepository()) {
            if (userRepository.isDuplicated(user.getUserName())) {
                throw new DuplicateUserNameException();
            }
            return userRepository.save(user);
        }
    }


    @Override
//    todo: isDuplicated(user.getUserName) for this method!!!
    public User edit(User user) throws Exception {
        try (UserRepository repository = new UserRepository()) {
            return repository.edit(user);
        }
    }

    @Override
    public User remove(long id) throws Exception {
        try (UserRepository repository = new UserRepository()) {
            return repository.remove(id);
        }
    }

    @Override
    public List<User> findAll() throws Exception {
        try (UserRepository repository = new UserRepository()) {
            return repository.findAll();
        }
    }

    @Override
    public User findById(long id) throws Exception {
        try (UserRepository repository = new UserRepository()) {
            return repository.findById(id);
        }
    }
    public String login (String username,String password) throws Exception{
        try (UserRepository userRepository = new UserRepository()) {
            if (login(username,password)!=null){
                System.out.println("login successful");

            }else System.out.println("login fail");

        }
    }
}
