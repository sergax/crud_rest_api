package com.sergax.crudrestapi.servlets;

import com.sergax.crudrestapi.model.File;
import com.sergax.crudrestapi.model.User;
import com.sergax.crudrestapi.service.FileService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet( urlPatterns = "/file/")
public class FileServlet extends HttpServlet {
    private FileService fileService = new FileService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new" -> showNewForm(request, response);
                case "/insert" -> insertFile(request, response);
                case "/delete" -> deleteFile(request, response);
                case "/edit" -> showEditForm(request, response);
                case "/update" -> updateFile(request, response);
                default -> listFile(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    public void listFile(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<File> listFiles = fileService.getAll();
        request.setAttribute("listFile", listFiles);
        RequestDispatcher dispatcher = request.getRequestDispatcher("file-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("file-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Long id = Long.valueOf((request.getParameter("id")));
        File existingFile = fileService.getById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("file-form.jsp");
        request.setAttribute("file", existingFile);
        dispatcher.forward(request, response);

    }

    private void insertFile(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        File newFile = new File(null, name, new ArrayList<>());
        fileService.create(newFile);
        response.sendRedirect("list");
    }

    private void updateFile(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        File updatedFile = new File(id, name, new ArrayList<>());
        fileService.update(updatedFile);
        response.sendRedirect("list");
    }

    private void deleteFile(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        fileService.delete(id);
        response.sendRedirect("list");
    }
}
