package com.service123.tiketing.model.repository;

import com.service123.tiketing.controller.exception.ContentNotFoundException;
import com.service123.tiketing.model.common.Jdbc;
import com.service123.tiketing.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                "INSERT INTO USER_TBL (ID,USER_ROLES,NAME,FAMILY,USER_NAME,PASSWORD,description,ACTIVE,DELETED) VALUES (?,?,?,?,?,?,?,?,?) "
        );
        statement.setLong(1,user.getId());
        statement.setString(2,user.getUserRoles().toString());
        statement.setString(3,user.getName());
        statement.setString(4,user.getFamily());
        statement.setString(5,user.getUserName());
        statement.setString(6,user.getPassword());
        statement.setString(7,user.getDescription());
        statement.setBoolean(8,user.isActive());
        statement.setBoolean(9,user.isDeleted());
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
                    "UPDATE USER_TBL SET USER_ROLES=?,NAME=?,FAMILY=?,USER_NAME=?,PASSWORD=?,description=?,ACTIVE=?,DELETED=? WHERE ID=?"
            );

            statement.setString(1,user.getUserRoles().toString());
            statement.setString(2, user.getName());
            statement.setString(3, user.getFamily());
            statement.setString(4, user.getUserName());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getDescription());
            statement.setBoolean(7, user.isActive());
            statement.setBoolean(8, user.isDeleted());
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
                    .userName(resultSet.getString("USER_NAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .active(resultSet.getBoolean("ACTIVE"))
                    .deleted(resultSet.getBoolean("DELETED"))
                    .build();
        }
        if (user!=null) {
            statement = connection.prepareStatement(
                    "UPDATE USER_TBL SET DELETED=? WHERE ID=?"
            );
            statement.setLong(1, id);
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
                "SELECT * FROM USER_TBL AND DELETED=0"
        );
        ResultSet resultSet=statement.executeQuery();
        List<User> userList=null;
        while (resultSet.next()){
            User user=User
                    .builder()
                    .id(resultSet.getLong("ID"))
                    //todo : .userRoles(UserRoles.valueOf(resultSet.getString("User_Role")))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .userName(resultSet.getString("USER_NAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .description(resultSet.getString("DESCRIPTION"))
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

    @Override
    public boolean isDuplicated(User user) throws Exception {
        connection= Jdbc.getJdbc().getConnection();
        statement=connection.prepareStatement(
                "SELECT COUNT(USER_NAME) AS C FROM USER_TBL  WHERE USER_NAME =? AND DELETED=0"
        );
        statement.setString(1, user.getUserName());
        ResultSet resultSet=statement.executeQuery();
        resultSet.next();
        int count=resultSet.getInt("C");
        return (count==0)?false:true;
    }

    @Override
    public User findById(long id) throws Exception { connection= Jdbc.getJdbc().getConnection();
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
                    //todo : .userRoles(UserRoles.valueOf(resultSet.getString("User_Role")))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .userName(resultSet.getString("USER_NAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .active(resultSet.getBoolean("ACTIVE"))
                    .deleted(resultSet.getBoolean("DELETED"))
                    .build();

        }
        if (user==null){
            throw new ContentNotFoundException("No User Found");
        }
        return user;
    }

    @Override
    public User login(User user) throws Exception {
        connection= Jdbc.getJdbc().getConnection();
        statement=connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE USER_NAME=? AND PASSWORD=?"
        );
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getPassword());
        ResultSet resultSet=statement.executeQuery();
         user=null;
        if (resultSet.next()){
            user=User
                    .builder()
                    .id(resultSet.getLong("ID"))
                    //todo : .userRoles(UserRoles.valueOf(resultSet.getString("User_Role")))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .userName(resultSet.getString("USER_NAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .active(resultSet.getBoolean("ACTIVE"))
                    .deleted(resultSet.getBoolean("DELETED"))
                    .build();

        }
        if (user==null){
            throw new ContentNotFoundException("No User Found");
        }
        return user;
    }


    @Override
    public void close() throws Exception {
        statement.close();
        connection.close();

    }
}
