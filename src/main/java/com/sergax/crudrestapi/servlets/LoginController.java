package com.sergax.crudrestapi.servlets;


import com.sergax.crudrestapi.dao.daoImpl.LoginDao;
import com.sergax.crudrestapi.model.User;
import com.sergax.crudrestapi.service.UserService;

import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/")
public class LoginController extends HttpServlet {
    private LoginDao loginDao;

    public void init( ) {
        loginDao = new LoginDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/login");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User loginBean = new User();
        loginBean.setUserName(username);
        loginBean.setPassword(password);

        try {
            if (loginDao.validate(loginBean)) {
                response.sendRedirect("file");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
