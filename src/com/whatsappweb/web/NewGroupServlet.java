package com.whatsappweb.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.whatsappweb.database.LoginDB;
import com.whatsappweb.database.NewGroupDB;
import com.whatsappweb.models.Groups;
import com.whatsappweb.models.Users;


@WebServlet("/createGroup")
public class NewGroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NewGroupDB newGroupDB;

    public void init() {
    	newGroupDB = new NewGroupDB();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String name = request.getParameter("name");
        String[] selectedUsers = request.getParameterValues("users");
        
        ArrayList<String> members = new ArrayList<>();
        
        for (String str : selectedUsers) {
        	members.add(str);
        }
        
        HttpSession session = request.getSession();
        String c_username = session.getAttribute("UserName").toString();
        members.add(c_username);
        
        Groups grp = new Groups();
        
        String group_id = System.currentTimeMillis()+"";
        grp.setGroupName(name);
        grp.setGroupId(group_id);
        grp.setMembers(members);

        try {
            if (newGroupDB.register(grp)) {
            	response.sendRedirect("users");
            } else {
            	response.sendRedirect("error.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        

        
    }
}