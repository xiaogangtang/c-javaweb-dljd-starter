package com.apexsoft.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/myServletRequestAttributeListener")
public class MyServletRequestAttributeListenerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("age",18);
        request.removeAttribute("age");

        request.setAttribute("age2",19);
        request.setAttribute("age2",22);
    }
}
