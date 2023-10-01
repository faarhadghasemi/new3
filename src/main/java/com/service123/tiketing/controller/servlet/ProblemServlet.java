package com.service123.tiketing.controller.servlet;

import com.service123.tiketing.model.entity.Problem;
import com.service123.tiketing.model.entity.enums.ActionType;
import com.service123.tiketing.model.service.ProblemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name ="problem", urlPatterns = "/problem.do")

public class ProblemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      ActionType action = null;
        String data = null;
        try {
            data = ProblemService.getProblemService().save(
                    Problem.builder()
                            .describtion(req.getParameter("describtion"))
                            .deleted(false)
                            .build()
            ).toString();
            action= ActionType.SAVE;
        }
        catch (Exception e){
            data = e.getMessage();
            action = ActionType.ERROR;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ActionType action = null;
        String data = null;
        List<Problem> Problemlist = new ArrayList<>();
        try {
            req.setAttribute("problem",Problemlist );
            action=ActionType.FOND;
        }
        catch (Exception e){
            data = e.getMessage();
            action=ActionType.ERROR;
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ActionType action = null;
        String data = null;
        try {
           data = String.valueOf(ProblemService.getProblemService().edit(
                    Problem.builder().id(Long.parseLong(req.getParameter("id")))
                            .describtion(req.getParameter("describtion"))
                            .deleted(false)
                            .build()));
                             action = ActionType.EDIT;
        }
        catch (Exception e){
            data = e.getMessage();
            action=ActionType.ERROR;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ActionType action = null;
        String data = null;
        try {
            Problem problem = ProblemService.getProblemService()
                    .remove(Long.parseLong(req.getParameter("id")));
            action =ActionType.REMOVE;

        } catch (Exception e){
            data = e.getMessage();
            action = ActionType.ERROR;

        }
    }
}
