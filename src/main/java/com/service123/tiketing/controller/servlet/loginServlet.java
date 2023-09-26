package com.service123.tiketing.controller.servlet;

import com.service123.tiketing.model.entity.User;
import com.service123.tiketing.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login",urlPatterns = "/login.do")
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = User.builder()
                    .userName(req.getParameter("username"))
                    .password(req.getParameter("password"))
                    .build();
            System.out.println(UserService.getService().save(user) + "Saved");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
