package com.service123.tiketing.model.repository;

import com.service123.tiketing.controller.exception.ContentNotFoundException;
import com.service123.tiketing.model.common.Jdbc;
import com.service123.tiketing.model.entity.Problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        statement.setString(2, problem.getDescribtion());
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
            statement.setString(2, problem.getDescribtion());
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
//        statement.setLong(1,problem.getId());
        ResultSet resultSet = statement.executeQuery();



        return null;
    }

    @Override
    public List<Problem> findAll() throws Exception {
        return null;
    }

    @Override
    public Problem findById(long id) throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {
        connection.close();
        statement.close();
    }
}
