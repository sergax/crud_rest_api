package com.sergax.crudrestapi.servlets;

import com.sergax.crudrestapi.dao.daoImpl.EventDaoImpl;
import com.sergax.crudrestapi.dao.daoImpl.UserDaoImpl;
import com.sergax.crudrestapi.model.Event;
import com.sergax.crudrestapi.model.File;
import com.sergax.crudrestapi.model.User;
import com.sergax.crudrestapi.service.EventService;
import com.sergax.crudrestapi.service.UserService;

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

@WebServlet(name = "EventServlet", urlPatterns = "/")
public class EventServlet extends HttpServlet {
    private EventService eventService = new EventService();
    private EventDaoImpl eventDao = new EventDaoImpl();
    private UserDaoImpl userDao = new UserDaoImpl();
    private UserService userService = new UserService();

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
                case "/insert" -> insertEvent(request, response);
                case "/delete" -> deleteEvent(request, response);
                case "/edit" -> showEditForm(request, response);
                case "/update" -> updateEvent(request, response);
                default -> listEvent(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    public void listEvent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
//        List<Event> listEvent = eventService.getAll();
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(name, password);
        List listEvent = eventDao.getUsersEvent(user);
        request.setAttribute("listEvent", listEvent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/event/event-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/event/event-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Long id = Long.valueOf((request.getParameter("id")));
        Event existingEvent = eventService.getById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/event/event-form.jsp");
        request.setAttribute("event", existingEvent);
        dispatcher.forward(request, response);
    }

    private void insertEvent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        Event newEvent = new Event(null, name, new File(), new User());
        eventService.create(newEvent);
        response.sendRedirect("list");
    }

    private void updateEvent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        Event updatedEvent = new Event(id, name, new File(), new User());
        eventService.update(updatedEvent);
        response.sendRedirect("list");
    }

    private void deleteEvent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        eventService.delete(id);
        response.sendRedirect("list");
    }
}
