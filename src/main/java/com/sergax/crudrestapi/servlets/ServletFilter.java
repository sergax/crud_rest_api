//package com.sergax.crudrestapi.servlets;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.ServletException;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//
//@WebServlet("/")
//public class ServletFilter implements Filter {
//    private FilterConfig filterConfig;
//    private static ArrayList<String> pages;  // хранилище страниц
//
//    public ServletFilter() {
//        // Создание хранилища страниц
//        if (pages == null)
//            pages = new ArrayList<String>();
//    }
//
//    @Override
//    public void destroy() {
//        filterConfig = null;
//    }
//
//    @Override
//    public void init(FilterConfig fConfig) throws ServletException {
//        filterConfig = fConfig;
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain filterChain) throws IOException, ServletException {
//        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {
//            HttpServletRequest req = (HttpServletRequest) request;
//            String[] list = req.getRequestURI().split("/");
//            String page = null;
//            if (list[list.length - 1].indexOf(".jsp") > 0) {
//                page = list[list.length - 1];
//            }
//            // Если открывается главная страница, то выполняем проверку
//            if ((page != null) && page.equalsIgnoreCase("login.jsp")) {
//                if (pages.contains("file-list.jsp") || pages.contains("file-user.jsp")) {
//                    filterChain.doFilter(request, response);
//                    return;
//                } else {
//                    ServletContext ctx = filterConfig.getServletContext();
//                    RequestDispatcher dispatcher = ctx.getRequestDispatcher("/login.jsp");
//                    dispatcher.forward(request, response);
//                    return;
//                }
//            } else if (page != null) {
//                if (!pages.contains(page))
//                    pages.add(page);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}
