package com.service123.tiketing.model.repository;

import com.service123.tiketing.model.common.Jdbc;
import com.service123.tiketing.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository implements RepositoryImpl<User> {

    private Connection connection;
    private PreparedStatement statement;

    @Override
    public User save(User user) throws Exception {
        user.setActive(true);
        user.setDeleted(false);
        user.setId(Jdbc.getJdbc().nextId("USER_SEQ"));
        connection= Jdbc.getJdbc().getConnection();
        statement=connection.prepareStatement(
                "INSERT INTO USER_TBL (ID,NAME,FAMILY,USER_NAME,PASSWORD,description,ACTIVE,DELETED) VALUES (?,?,?,?,?,?,?,?) "
        );
        statement.setLong(1,user.getId());
        statement.setString(2,user.getName());
        statement.setString(3,user.getFamily());
        statement.setString(4,user.getUserName());
        statement.setString(5,user.getPassword());
        statement.setString(6,user.getDescription());
        statement.setBoolean(7,user.isActive());
        statement.setBoolean(8,user.isDeleted());
        statement.execute();
        return user;
    }

    @Override
    public User edit(User user) throws Exception {
        connection= Jdbc.getJdbc().getConnection();
        statement=connection.prepareStatement(
                "UPDATE USER_TBL SET NAME=?,FAMILY=?,USER_NAME=?,PASSWORD=?,description=?,ACTIVE=?,DELETED=? WHERE ID=?"
        );

        statement.setString(1,user.getName());
        statement.setString(2,user.getFamily());
        statement.setString(3,user.getUserName());
        statement.setString(4,user.getPassword());
        statement.setString(5,user.getDescription());
        statement.setBoolean(6,user.isActive());
        statement.setBoolean(7,user.isDeleted());
        statement.execute();
        return user;
    }

    @Override
    public User remove(long id) throws Exception {
        connection= Jdbc.getJdbc().getConnection();
        statement=connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE ID=?"
        );
        ResultSet resultSet=statement.executeQuery();
        User user=null;
        if (resultSet.next()){
            user=User
                    .builder()
                    .id(resultSet.getLong("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .userName(resultSet.getString("USER_NAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .active(resultSet.getBoolean("ACTIVE"))
                    .deleted(resultSet.getBoolean("DELETED"))
                    .build()
        }
        if (user!=null) {
            statement = connection.prepareStatement(
                    "UPDATE USER_TBL SET DELETED=? WHERE ID=?"
            );
            statement.setLong(1, id);
            statement.execute();
        }else {
            throw new ContentNotFoundException
        }
        return user;
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
