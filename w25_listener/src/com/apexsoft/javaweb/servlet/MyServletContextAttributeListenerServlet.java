package com.apexsoft.javaweb.servlet;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/servletContextAttributeListener")
public class MyServletContextAttributeListenerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("key","0001");
        servletContext.removeAttribute("key");

        servletContext.setAttribute("key2","0002");
        servletContext.setAttribute("key2","0002-new");

    }
}
