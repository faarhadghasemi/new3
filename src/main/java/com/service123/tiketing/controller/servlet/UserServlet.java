package com.service123.tiketing.controller.servlet;

import com.service123.tiketing.model.entity.User;
import com.service123.tiketing.model.entity.enums.ActionType;
import com.service123.tiketing.model.entity.enums.UserRoles;
import com.service123.tiketing.model.repository.LogDa;
import com.service123.tiketing.model.service.ProblemService;
import com.service123.tiketing.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/user.do")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       ActionType actionType=null;
       String data = null;
        List<User> userList = new ArrayList<>();
        try {
            request.setAttribute("user","UserList");
        }catch (Exception e){
            data = e.getMessage();
            actionType = ActionType.ERROR;
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       ActionType action=null;
       String data=null;
        UserRoles userRoles;

        try {
            data= UserService.getService().save(User
                    .builder()
                    .id(Long.parseLong(request.getParameter("id")))
                    .userRoles(UserRoles.valueOf(request.getParameter("userRoles")))
                    .name(request.getParameter("name"))
                    .family(request.getParameter("family"))
                    .userName(request.getParameter("username"))
                    .password(request.getParameter("password"))
                    .deleted(false)
                    .build()
            ).toString();
            action= ActionType.SAVE;
            System.out.println(data);

        }catch (Exception e){
            data=e.getMessage();
            action=ActionType.ERROR;
        }finally {
            try {LogDa.log(action,data,1);}
            catch (Exception e){
                System.out.println("ERROR CONNECTING LOG SERVER");
            }
        }
        response.sendRedirect("/userIndex.jsp");
    }//:SAVE METHOD;

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = null;
        ActionType actionType = null;

        try {
            data = String.valueOf(UserService.getService().edit(User.builder()
                            .id(Long.parseLong(req.getParameter("id")))
                            .userRoles(UserRoles.valueOf(req.getParameter("userrole")))
                            .name(req.getParameter("name"))
                            .family(req.getParameter("family"))
                            .userName(req.getParameter("username"))
                            .password(req.getParameter("password"))
                            .description(req.getParameter("description"))
                            .deleted(false)
                            .build()));

            actionType = ActionType.EDIT;
        }
        catch (Exception e){
            data = e.getMessage();
            actionType = ActionType.ERROR;

        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = null;
        ActionType action=null;
        try {
            User user =UserService.getService().remove(Long.parseLong(req.getParameter("id")));
            action = ActionType.REMOVE;
        }catch (Exception e){
            data = e.getMessage();
            action = ActionType.ERROR;
        }
    }
}
