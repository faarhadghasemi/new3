package com.service123.tiketing.model.repository;

import com.service123.tiketing.model.common.Jdbc;
import com.service123.tiketing.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserRepository implements RepositoryImpl<User> {

    private Connection connection;
    private PreparedStatement statement;

    @Override
    public User save(User user) throws Exception {
        connection= Jdbc.getJdbc().getConnection();
        statement=connection.prepareStatement(
                "INSERT "
        );
        return user;
    }

    @Override
    public User edit(User user) throws Exception {
        return null;
    }

    @Override
    public User remove(long id) throws Exception {
        return null;
    }

    @Override
    public List<User> findAll() throws Exception {
        return null;
    }

    @Override
    public User findById(long id) throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {
        statement.close();
        connection.close();

    }
}
