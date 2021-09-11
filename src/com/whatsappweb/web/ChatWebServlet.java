package com.whatsappweb.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.whatsappweb.database.ChatDao;
import com.whatsappweb.models.GroupListMessage;
import com.whatsappweb.models.Groups;
import com.whatsappweb.models.ListMessage;
import com.whatsappweb.models.Message;
import com.whatsappweb.models.Users;

@WebServlet("/")
public class ChatWebServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ChatDao chatDao;

    public void init() {
    	chatDao = new ChatDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
		    case "/signin":
		        showLogin(request, response);
		        break;
		    case "/signup":
		        showRegister(request, response);
		        break;
		    case "/new_group":
		    	showNewGroup(request, response);
		        break;
		        
		    case "/chat":
			try {
				listUser(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        break;
		        
		    case "/onechat":
			try {
				chat(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        break;
		        
		    case "/groupchat":
				try {
					groupChat(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			        break;
		    
		    default:
		    	//showLogin(request, response);
		        break;
		}
    }

    private void showLogin(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showRegister(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
    	        dispatcher.forward(request, response);
    }
    
    private void showNewGroup(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
		    	HttpSession session = request.getSession();
		    	String current_username = session.getAttribute("UserName").toString();
		        List <Users> listUser = chatDao.selectAllUsers(current_username);
		        request.setAttribute("listUser", listUser);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("new_group.jsp");
    	        dispatcher.forward(request, response);
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	
		    	HttpSession session = request.getSession();
		    	String current_username = session.getAttribute("UserName").toString();
    	        List <Users> listUser = chatDao.selectAllUsers(current_username);
    	        List <Groups> listGroup = chatDao.selectAllGroups(current_username);
    	        
    	        request.setAttribute("listUser", listUser);
    	        request.setAttribute("listGroup", listGroup);
    	        
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("chat.jsp");
    	        dispatcher.forward(request, response);
    }
    
    private void chat(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        String username = request.getParameter("id");
    	        String type = request.getParameter("type");
    	        System.out.print(type);
    	        request.setAttribute("type", type);
    	        request.setAttribute("id", username);
    	        
    	        HttpSession session = request.getSession();
		    	String current_username = session.getAttribute("UserName").toString();
    	        
    	        List <ListMessage> messagesList = chatDao.getUserMessages(username, current_username);
    	        request.setAttribute("listMessages", messagesList);
    	        
    	        List <Users> listUser = chatDao.selectAllUsers(current_username);
    	        List <Groups> listGroup = chatDao.selectAllGroups(current_username);
    	        
    	        request.setAttribute("listUser", listUser);
    	        request.setAttribute("listGroup", listGroup);
    	       
    	        
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("chat.jsp");
    	        try {
					dispatcher.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    }
    
    private void groupChat(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        String group_id = request.getParameter("id");
    	        String group_name = request.getParameter("name");
    	        request.setAttribute("id", group_id);
    	        String type = request.getParameter("type");
    	        System.out.print(type);
    	        request.setAttribute("type", type);
    	        request.setAttribute("name", group_name);
    	        
    	        HttpSession session = request.getSession();
		    	String current_username = session.getAttribute("UserName").toString();
    	        
    	        List <GroupListMessage> messagesList = chatDao.getGroupMessages(group_id, current_username);
    	        List <String> listMembers = chatDao.selectAllGroupMembers(group_id);
    	        
    	        List <Users> listUser = chatDao.selectAllUsers(current_username);
    	        List <Groups> listGroup = chatDao.selectAllGroups(current_username);
    	        
    	        request.setAttribute("listUser", listUser);
    	        request.setAttribute("listGroup", listGroup);
    	        
    	        for (int i=0;i<listMembers.size();i++) {
    	        	if (listMembers.get(i).equals(current_username)) {
    	        		listMembers.remove(i);
    	        	}
    	        }
    	        
    	        listMembers.add("you");
    	        
    	        
    	        
    	        
    	        request.setAttribute("listMessages", messagesList);
    	        request.setAttribute("listMembers", listMembers);
    	        
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("chat.jsp");
    	        try {
					dispatcher.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    }
    
    
    
}