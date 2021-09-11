package com.whatsappweb.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whatsappweb.database.RegisterDB;
import com.whatsappweb.models.Users;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RegisterDB registerDB;

    public void init() {
    	registerDB = new RegisterDB();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);


        try {
            if (registerDB.register(user)) {
                response.sendRedirect("registersuccess.jsp");
            } else {
            	response.sendRedirect("error.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}