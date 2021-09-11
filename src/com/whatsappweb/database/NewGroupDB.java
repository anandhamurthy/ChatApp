package com.whatsappweb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

import com.whatsappweb.models.Groups;
import com.whatsappweb.models.Users;


public class NewGroupDB {

	public boolean register(Groups grp) throws ClassNotFoundException {
        boolean status = false;
        
        ChatDao chatDao = new ChatDao();

        Class.forName("com.mysql.jdbc.Driver");

        try {
        	
        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root","");
        	
        	if (chatDao.insertGroup(grp.getGroupId(),grp.getGroupName())) {
        		status = true;
        		for (String member: grp.getMembers()) {
            		if (chatDao.insertGroupMembers(grp.getGroupId(), member)) {
            			status = true;
            		}else {
            			status = false;
            		}
            	}
        	}else {
        		status = false;
        	}
        	
        	
        	
        	
            
        	
            

        } catch (SQLException e) {
            printSQLException(e);
        }
        return status;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
