

import java.sql.Connection;
import java.sql.SQLException;

package com.service123.tiketing.model.common;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbc {
    private static  Jdbc jdbc=new Jdbc();
    private static  BasicDataSource basicDataSource=new BasicDataSource();

    private Jdbc() {
    }

    public static Jdbc getJdbc() {
        return jdbc;
    }
    public Connection getConnection() throws SQLException {
        basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        basicDataSource.setUsername("java");
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
        Long id=resultSet.getLong("ID");
        statement.close();
        connection.close();
        return id;


    }
}

