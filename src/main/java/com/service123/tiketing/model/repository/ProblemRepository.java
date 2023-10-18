package com.service123.tiketing.model.repository;
import com.service123.tiketing.controller.exception.ContentNotFoundException;
import com.service123.tiketing.model.common.Jdbc;
import com.service123.tiketing.model.entity.Problem;
import com.service123.tiketing.model.entity.User;
import com.service123.tiketing.model.entity.enums.ProblemStatus;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProblemRepository implements RepositoryImpl<Problem> {
    private Connection connection;
    private PreparedStatement statement;


    public ProblemRepository() {
    }

    @Override
    public Problem save(Problem problem) throws Exception {
problem.setId(Jdbc.getJdbc().nextId("PROBLEM_SEQ"));
        connection = Jdbc.getJdbc().getConnection();
        statement = connection.prepareStatement(
                "INSERT INTO PROBLEM_TBL (ID,PARENT_ID,DESCRIPTION,date_Time,sender,receiver,answer,status,deleted) values (?,?,?,?,?,?,?,?,?) "
        );
        statement.setLong(1, problem.getId());
        statement.setLong(2, (problem.getParentId()==null)?0:problem.getParentId());
        statement.setString(3, problem.getDescription());
        LocalDateTime localDateTime = java.time.LocalDateTime.now();
        statement.setTimestamp(4, Timestamp.valueOf(localDateTime));
        statement.setString(5, problem.getSender().getUserName());
        statement.setString(6,problem.getReceiver().getUserName());
        statement.setString(7, problem.getAnswer());
        statement.setString(8, problem.getStatus().toString());
        statement.setBoolean(9, problem.isDeleted());
        statement.execute();

        return problem;
    }

    @Override
    public Problem edit(Problem problem) throws Exception {

        connection = Jdbc.getJdbc().getConnection();
        statement = connection.prepareStatement(
                "Select * FROM PROBLEM_TBL where id=? and deleted=0 "
        );
        statement.setLong(1, problem.getId());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            connection = Jdbc.getJdbc().getConnection();
            statement = connection.prepareStatement(
                    "Update PROBLEM_TBL set PARENT_ID=ID,DESCRIPTION=?,date_Time=?,sender=?,receiver=?,answer=?,status=?,deleted=? where id=?"
            );
//            statement.setLong(1, problem.getParentId());
            statement.setString(1, problem.getDescription());
            LocalDateTime localDateTime = java.time.LocalDateTime.now();
            statement.setTimestamp(2, Timestamp.valueOf(localDateTime));
            statement.setString(3, problem.getSender().getUserName());
            statement.setString(4, problem.getReceiver().getUserName());
            statement.setString(5, problem.getAnswer());
            statement.setString(6, problem.getStatus().toString());
            statement.setBoolean(7, problem.isDeleted());
            statement.setLong(8, problem.getId());
            statement.execute();

        } else {
            problem = null;
            throw new ContentNotFoundException("problem  Not Found");

        }
        return problem;
    }

    @Override
    public Problem remove(long id) throws Exception {
        Problem problem=null;
        connection = Jdbc.getJdbc().getConnection();
        statement = connection.prepareStatement(
                "SELECT * FROM PROBLEM_TBL WHERE id=?"
        );
        statement.setLong(1,id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            problem=Problem.builder()
                    .id(resultSet.getLong("ID"))
//                    .parentId(resultSet.getLong("PARENTId"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .dateTime(resultSet.getDate("DATE_TIME").toLocalDate())
                    .sender(User.builder().userName(resultSet.getString("SENDER")).build())
                    .receiver(User.builder().userName(resultSet.getString("RECEIVER")).build())
                    .answer(resultSet.getString("ANSWER"))
                    .status(ProblemStatus.valueOf(resultSet.getString("STATUS")))
                    .deleted(resultSet.getBoolean("DELETED"))
                    .build();
        }
        if (problem != null){
            statement = connection.prepareStatement(
                    "Update  PROBLEM_TBL set deleted=1 where id=?");
            statement.setLong(1,problem.getId());
            statement.execute();
        }
        else {
            throw new ContentNotFoundException("problem not found");
        }

        return problem;
    }

    @Override
    public List<Problem> findAll() throws Exception {
        Problem problem=null;
        connection = Jdbc.getJdbc().getConnection();
        statement = connection.prepareStatement(
                "SELECT * FROM PROBLEM_TBL WHERE deleted=0 "
        );
        ResultSet resultSet = statement.executeQuery();
        List<Problem> problemList = new ArrayList<>();
        while (resultSet.next()){
            problem =  Problem.builder()
                    .id(resultSet.getLong("ID"))
//                    .parentId(resultSet.getLong("PARENTId"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .dateTime(resultSet.getDate("DATE_TIME").toLocalDate())
                    .sender(User.builder().userName(resultSet.getString("SENDER")).build())
                    .receiver(User.builder().userName(resultSet.getString("RECEIVER")).build())
                    .answer(resultSet.getString("ANSWER"))
                    .status(ProblemStatus.valueOf(resultSet.getString("STATUS")))
                    .deleted(resultSet.getBoolean("DELETED"))
                    .build();
            problemList.add(problem);
        }
        if(problemList.isEmpty())
            throw new ContentNotFoundException("problem noy found");
        return problemList;
    }


    @Override
    public Problem findById(long id) throws Exception {

        connection = Jdbc.getJdbc().getConnection();
        statement = connection.prepareStatement(
                "select * from PROBLEM_TBL where deleted=0 and id=?");
        statement.setLong(1,id);
        ResultSet resultSet = statement.executeQuery();
        Problem problem=null;
        while (resultSet.next()){
            problem =Problem.builder()
                    .id(resultSet.getLong("ID"))
//                    .parentId(resultSet.getLong("PARENTId"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .dateTime(resultSet.getDate("DATE_TIME").toLocalDate())
                    .sender(User.builder().userName(resultSet.getString("SENDER")).build())
                    .receiver(User.builder().userName(resultSet.getString("RECEIVER")).build())
                    .answer(resultSet.getString("ANSWER"))
                    .status(ProblemStatus.valueOf(resultSet.getString("STATUS")))
                    .deleted(resultSet.getBoolean("DELETED"))
                    .build();
        }
        if (problem == null){
            throw new ContentNotFoundException("problem not found");
        }
        return problem;
    }


    public Problem findByParentId(long parentId) throws Exception {

        connection = Jdbc.getJdbc().getConnection();
        statement = connection.prepareStatement(
                "select * from PROBLEM_TBL where deleted=0 and PARENT_ID=?");
        statement.setLong(1,parentId);
        ResultSet resultSet = statement.executeQuery();
        Problem problem=null;
        while (resultSet.next()){
            problem =Problem.builder()
                    .id(resultSet.getLong("ID"))
                    .parentId(resultSet.getLong("PARENTId"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .dateTime(resultSet.getDate("DATETIME").toLocalDate())
                    .sender(User.builder().userName(resultSet.getString("SENDER")).build())
                    .receiver(User.builder().userName(resultSet.getString("RECEIVER")).build())
                    .answer(resultSet.getString("ANSWER"))
                    .status(ProblemStatus.valueOf(resultSet.getString("STATUS")))
                    .deleted(resultSet.getBoolean("DELETED"))
                    .build();
        }
        if (problem == null){
            throw new ContentNotFoundException("problem not found");
        }
        return problem;
    }
    @Override
    public void close() throws Exception {
        connection.close();
        statement.close();
    }
}
