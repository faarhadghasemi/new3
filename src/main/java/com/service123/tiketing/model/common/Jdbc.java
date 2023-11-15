package com.service123.tiketing.model.common;

import java.sql.Connection;
import java.sql.SQLException;
import lombok.Getter;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbc {
    @Getter
    private static final Jdbc jdbc=new Jdbc();
    private static final BasicDataSource basicDataSource=new BasicDataSource();

    private Jdbc() {
    }

    public Connection getConnection() throws SQLException {
        basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        basicDataSource.setUsername("javase");
        basicDataSource.setPassword("java123");
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxTotal(20);
        return basicDataSource.getConnection();
    }
    public long nextId(String sequenceName) throws SQLException {
        Connection connection= jdbc.getConnection();
        PreparedStatement statement= connection.prepareStatement(
                "SELECT " + sequenceName +".NEXTVAL AS ID FROM DUAL"
        );
        ResultSet resultSet=statement.executeQuery();
        resultSet.next();
        long id=resultSet.getLong("ID");
        statement.close();
        connection.close();
        return id;


    }
}

