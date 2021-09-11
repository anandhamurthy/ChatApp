package com.whatsappweb.web;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.whatsappweb.database.ChatDao;
import com.whatsappweb.models.Users;

@WebServlet("/UploadAudFile")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class UploadAudFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 ChatDao chatDao = new ChatDao();
	 
	 private static final String GET_AUDIO_SQL = "select audio from audio_storage where filename = ?;";
       
	 
	 private String jdbcURL = "jdbc:mysql://localhost:3306/demo";
	    private String jdbcUsername = "root";
	    private String jdbcPassword = "";
	    
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String fileName = request.getParameter("filename");
			
			try (Connection connection = getConnection();
		            PreparedStatement preparedStatement = connection.prepareStatement(GET_AUDIO_SQL);) {
				preparedStatement.setString(1, fileName);
		            System.out.println(preparedStatement);
		            ResultSet rs = preparedStatement.executeQuery();
		            if (rs.next()) {
		                Blob blob = rs.getBlob("audio");
		                InputStream inputStream = blob.getBinaryStream();
		                int fileLength = inputStream.available();
		                 
		                System.out.println("fileLength = " + fileLength);
		 
		                ServletContext context = getServletContext();
		 
		                // sets MIME type for the file download
		                String mimeType = context.getMimeType(fileName);
		                if (mimeType == null) {        
		                    mimeType = "application/octet-stream";
		                }              
		                 
		                response.setContentType(mimeType);
		                response.setContentLength(fileLength);
		                String headerKey = "Content-Disposition";
		                String headerValue = String.format("attachment; filename=\"%s\"", fileName);
		                response.setHeader(headerKey, headerValue);
		 
		                // writes the file to the client
		                OutputStream outStream = response.getOutputStream();
		                 
		                byte[] buffer = new byte[1024];
		                int bytesRead = -1;
		                 
		                while ((bytesRead = inputStream.read(buffer)) != -1) {
		                    outStream.write(buffer, 0, bytesRead);
		                }
		                 
		                inputStream.close();
		                outStream.close();  
		                
		            }
		        } catch (SQLException e) {
		            printSQLException(e);
		        }
			
			
			
			
			System.out.println("File downloaded at client successfully");
			
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String filename = request.getParameter("filename").toString();
		
		System.out.println(filename);

		InputStream inputStream = null;

        String message = null;
        
        Part filePart = request.getPart("file");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        
        
        try {
			if (chatDao.insertAudio(filename, inputStream)) {
			    message = "File uploaded and saved into database";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        request.setAttribute("Message", message);

        getServletContext().getRequestDispatcher("/success.jsp")
            .forward(request, response);
	    
	}

	protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
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
