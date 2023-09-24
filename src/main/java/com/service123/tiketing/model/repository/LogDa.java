package com.service123.tiketing.model.repository;

import com.service123.tiketing.model.common.Jdbc;
import com.service123.tiketing.model.entity.enums.ActionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LogDa {
    public static void log(ActionType actionType,String data,long userId) throws SQLException{
        Connection connection= Jdbc.getJdbc().getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement(
                "INSERT  INTO LOG_TBL(ID,ACTION,DATA,DATE_TIME,USER_ID) VALUES  (LOG_SEQ.NEXTVAL,?,?,?,?)"
        );
        preparedStatement.setString(1,actionType.name());
        preparedStatement.setString(2,data);
        Timestamp timestamp=Timestamp.valueOf(LocalDateTime.now());
        preparedStatement.setTimestamp(3,timestamp );
        preparedStatement.setLong(4,userId);
        preparedStatement.execute();
        System.out.printf("%s - [%s] - Data : %s - By User %s",timestamp,actionType.name(),data,userId);
        preparedStatement.close();
        connection.close();
    }
}
