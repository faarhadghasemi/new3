package com.service123.tiketing.model.repository;

import com.service123.tiketing.controller.exception.ContentNotFoundException;
import com.service123.tiketing.model.common.Jdbc;
import com.service123.tiketing.model.entity.User;
import com.service123.tiketing.model.entity.enums.UserRoles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
                "INSERT INTO USER_TBL (ID,USER_ROLES,NAME,FAMILY,USERNAME,PASSWORD,ACTIVE,DELETED) VALUES (?,?,?,?,?,?,?,?) "
        );
        statement.setLong(1,user.getId());
        statement.setString(2,user.getUserRoles().toString());
        statement.setString(3,user.getName());
        statement.setString(4,user.getFamily());
        statement.setString(5,user.getUserName());
        statement.setString(6,user.getPassword());
        statement.setBoolean(7,user.isActive());
        statement.setBoolean(8,user.isDeleted());
        statement.execute();
        return user;
    }

    @Override
    public User edit(User user) throws Exception {
        connection= Jdbc.getJdbc().getConnection();
        statement=connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE ID=?"
        );
        statement.setLong(1,user.getId());
        ResultSet resultSet=statement.executeQuery();

        if (resultSet.next()) {
            connection = Jdbc.getJdbc().getConnection();
            statement = connection.prepareStatement(
                    "UPDATE USER_TBL SET USER_ROLES=?,NAME=?,FAMILY=?,USERNAME=?,PASSWORD=?,ACTIVE=?,DELETED=? WHERE ID=?"
            );

            statement.setString(1,user.getUserRoles().toString());
            statement.setString(2, user.getName());
            statement.setString(3, user.getFamily());
            statement.setString(4, user.getUserName());
            statement.setString(5, user.getPassword());
            statement.setBoolean(6, user.isActive());
            statement.setBoolean(7, user.isDeleted());
            statement.setLong(8, user.getId());
            statement.execute();
        }else {
            user=null;
            throw new ContentNotFoundException("User Not Found");
        }
        return user;
    }

    @Override
    public User remove(long id) throws Exception {
        connection= Jdbc.getJdbc().getConnection();
        statement=connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE ID=? AND DELETED=0"
        );
        statement.setLong(1,id);
        ResultSet resultSet=statement.executeQuery();
        User user=null;
        if (resultSet.next()){
            user=User
                    .builder()
                    .id(resultSet.getLong("ID"))
                  //todo : .userRoles(UserRoles.valueOf(resultSet.getString("User_Role")))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .userName(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .active(resultSet.getBoolean("ACTIVE"))
                    .deleted(resultSet.getBoolean("DELETED"))
                    .build();
        }
        if (user!=null) {
            statement = connection.prepareStatement(
                    "UPDATE USER_TBL SET DELETED=1 WHERE ID=?"
            );
            statement.setLong(1,user.getId());
            statement.execute();
        }else {
            throw new ContentNotFoundException("User Not Found");
        }
        return user;
    }

    @Override
    public List<User> findAll() throws Exception {
        connection= Jdbc.getJdbc().getConnection();
        statement=connection.prepareStatement(
                "SELECT * FROM USER_TBL where DELETED=0"
        );
        ResultSet resultSet=statement.executeQuery();
        List<User> userList=new ArrayList<>();
        while (resultSet.next()){
            User user=User
                    .builder()
                    .id(resultSet.getLong("ID"))
                    .userRoles(UserRoles.valueOf(resultSet.getString("USER_ROLES")))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .userName(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .active(resultSet.getBoolean("ACTIVE"))
                    .deleted(resultSet.getBoolean("DELETED"))
                    .build();
            userList.add(user);
        }
        if (userList==null){
            throw new ContentNotFoundException("No User Found");
        }
        return userList;
    }

    public boolean isDuplicated(String userName) throws Exception {
        connection= Jdbc.getJdbc().getConnection();
        statement=connection.prepareStatement(
                "SELECT COUNT(USERNAME) AS C FROM USER_TBL  WHERE USERNAME =?"
        );
        statement.setString(1, userName);
        ResultSet resultSet=statement.executeQuery();
        resultSet.next();
        int count=resultSet.getInt("C");
        return (count==0)?false:true;
    }

    @Override
    public User findById(long id) throws Exception {
        connection= Jdbc.getJdbc().getConnection();
        statement=connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE ID=? AND DELETED=0"
        );
        statement.setLong(1,id);
        ResultSet resultSet=statement.executeQuery();
        User user=null;
        while (resultSet.next()){
            user=User
                    .builder()
                    .id(resultSet.getLong("ID"))
                    .userRoles(UserRoles.valueOf(resultSet.getString("User_Role")))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .userName(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .active(resultSet.getBoolean("ACTIVE"))
                    .deleted(resultSet.getBoolean("DELETED"))
                    .build();

        }
        if (user==null){
            throw new ContentNotFoundException("No User Found");
        }
        return user;
    }

    public String login(String username,String password) throws Exception {
        connection= Jdbc.getJdbc().getConnection();
        statement=connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE USERNAME=? AND PASSWORD=?"
        );
        statement.setString(1,username);
        statement.setString(2, password);
        ResultSet resultSet=statement.executeQuery();
        resultSet.next();
        if (username ==null){
            throw new ContentNotFoundException("No User Found");
        }
        return username;
    }

//    public boolean findByUserName(String username) throws Exception {
//        connection= Jdbc.getJdbc().getConnection();
//        statement=connection.prepareStatement(
//                "SELECT * FROM USER_TBL WHERE USERNAME=? AND PASSWORD=?"
//        );
//        statement.setString(1, username);
//        statement.setString(2, password);
//        ResultSet resultSet=statement.executeQuery();
//        resultSet.next();
//        User user = null;
//        if (username ==null){
//            throw new ContentNotFoundException("No UserName Found");
//        }
//
//        return (username);
//    }
//

    @Override
    public void close() throws Exception {
        statement.close();
        connection.close();

    }
}
