package com.whatsappweb.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.whatsappweb.models.ReturnMessage;

@WebServlet("/DecryptMessage")
public class DecryptMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    
    public DecryptMessage() {
        super();
       
    }

	public void init(ServletConfig config) throws ServletException {
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = request.getParameter("message");
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		System.out.print(message+" "+from+" "+to);
		
		ReturnMessage returnMessage = new ReturnMessage();
		returnMessage.message = decrypt(message, from+to);
		
		out.print(new Gson().toJson(returnMessage));
		out.flush();
		out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
	}
	
	private String decrypt(String message, String key) {
		try {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            String SALT = "whatsappweb";
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
  
            KeySpec spec = new PBEKeySpec(key.toCharArray(), SALT.getBytes(),65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
  
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey,ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(message)));
        }
        catch (Exception e) {
            System.out.println("Error while decrypting: "
                               + e.toString());
        }
        return null;
    
	}

}
