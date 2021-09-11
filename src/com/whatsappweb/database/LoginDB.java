package com.whatsappweb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.websocket.Session;

import com.whatsappweb.models.Users;


public class LoginDB {

    public boolean validate(Users user) throws ClassNotFoundException {
        boolean status = false;

        Class.forName("com.mysql.jdbc.Driver");

        try {
        	
        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root","");
    		
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ? and password = ? ");
            preparedStatement.setString(1, user.getUsername());
            
            //String encodedPassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
            preparedStatement.setString(2, user.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
            
            

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
