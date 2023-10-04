package com.service123.tiketing.model.repository;

import com.service123.tiketing.controller.exception.ContentNotFoundException;
import com.service123.tiketing.model.common.Jdbc;
import com.service123.tiketing.model.entity.Problem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProblemRepository implements RepositoryImpl<Problem> {
    private Connection connection;
    private PreparedStatement statement;


    public ProblemRepository() {
    }

    @Override
    public Problem save(Problem problem) throws Exception {

        connection = Jdbc.getJdbc().getConnection();
        statement = connection.prepareStatement(
                "INSERT INTO PROBLEM_TBL values id=?,describtion=?,deleted=?"
        );
        statement.setLong(1, problem.getId());
        statement.setString(2, problem.getDescription());
        statement.setBoolean(3, problem.isDeleted());
        statement.execute();

        return problem;
    }

    @Override
    public Problem edit(Problem problem) throws Exception {

        connection = Jdbc.getJdbc().getConnection();
        statement = connection.prepareStatement(
                "Select * FROM PROBLEM_TBL where id=? "
        );
        statement.setLong(1, problem.getId());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            connection = Jdbc.getJdbc().getConnection();
            statement = connection.prepareStatement(
                    "Update PROBLEM_TBL set id=?,describtion=? and deleted=0"
            );
            statement.setLong(1, problem.getId());
            statement.setString(2, problem.getDescription());
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
        statement.setLong(1,problem.getId());
        ResultSet resultSet = statement.executeQuery();
        Problem problem1=null;
        if (resultSet.next()){
            Problem.builder()
                    .id(resultSet.getLong("id"))
                    .description(resultSet.getString("description"))
                    .build();
        }
        if(problem != null){
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
                "SELECT * FROM PROBLEM_TBL WHERE deleted=0"
        );
        ResultSet resultSet = statement.executeQuery();
        List<Problem> problemList = new ArrayList<>();
        if (resultSet.next()){
            problem =  Problem.builder()
                    .id(resultSet.getLong("id"))
                    .description(resultSet.getString("description"))
                    .build();
            problemList.add(problem);
        }
        if(problemList.size() == 0)
            throw new ContentNotFoundException("problem noy found");
        return problemList;
    }

    public boolean isDuplicated(Problem problem) throws Exception {
        return false;
    }

    @Override
    public Problem findById(long id) throws Exception {

        connection = Jdbc.getJdbc().getConnection();
        statement = connection.prepareStatement("select  * from PROBLEM_TBL where deleted=0 and  id=?");
        statement.setLong(1,id);
        ResultSet resultSet = statement.executeQuery();
        Problem problem=null;
        while (resultSet.next()){
            problem =Problem.builder()
                    .id(resultSet.getLong("id"))
                    .description(resultSet.getString("description"))
                    .build();
        }
        if (problem == null){
            throw new ContentNotFoundException("problem not found");
        }
        return problem;
    }

    public Problem login(Problem problem) throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {
        connection.close();
        statement.close();
    }
}
