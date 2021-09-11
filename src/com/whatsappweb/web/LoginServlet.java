package com.whatsappweb.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.whatsappweb.database.LoginDB;
import com.whatsappweb.models.Users;


@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private LoginDB loginDB;

    public void init() {
    	loginDB = new LoginDB();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        
//        sec-fetch-mode-navigate
//        content-length-30
//        referer-http://localhost:8080/websocket-app/
//        sec-fetch-site-same-origin
//        accept-language-en-IN,en-GB;q=0.9,en-US;q=0.8,en;q=0.7
//        cookie-JSESSIONID=6BF8ED617696FA8A30F93FBF9399D0DB; _ga=GA1.1.1022425151.1624783220; __gads=ID=4cd12f288fc5e58c-22f42e0606ca004b:T=1624798224:RT=1624798224:S=ALNI_Mawsg8BuFcqzxV5o-S0V4F4KsgFWA; _ga_2EN3JTP1R7=GS1.1.1626845167.26.1.1626845724.0
//        origin-http://localhost:8080
//        sec-fetch-user-?1
//        accept-text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
//        sec-ch-ua-"Chromium";v="92", " Not A;Brand";v="99", "Google Chrome";v="92"
//        sec-ch-ua-mobile-?0
//        host-localhost:8080
//        upgrade-insecure-requests-1
//        connection-keep-alive
//        content-type-application/x-www-form-urlencoded
//        cache-control-max-age=0
//        accept-encoding-gzip, deflate, br
//        user-agent-Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36
//        sec-fetch-dest-document
        
        
        
        
        
//        content-length - 30
//        referer - http://192.168.18.6:8080/websocket-app/
//        accept-language - en-IN,en-GB;q=0.9,en-US;q=0.8,en;q=0.7
//        cookie - JSESSIONID=841D132F504D531A4741DFEC28F19910
//        origin - http://192.168.18.6:8080
//        accept - text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
//        host - 192.168.18.6:8080
//        upgrade-insecure-requests - 1
//        connection - keep-alive
//        content-type - application/x-www-form-urlencoded
//        cache-control - max-age=0
//        accept-encoding - gzip, deflate
//        user-agent - Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36
        
        
//        sec-fetch-mode - navigate
//        content-length - 30
//        referer - http://localhost:8080/websocket-app/
//        sec-fetch-site - same-origin
//        accept-language - en-IN,en-GB;q=0.9,en-US;q=0.8,en;q=0.7
//        cookie - JSESSIONID=6BF8ED617696FA8A30F93FBF9399D0DB; _ga=GA1.1.1022425151.1624783220; __gads=ID=4cd12f288fc5e58c-22f42e0606ca004b:T=1624798224:RT=1624798224:S=ALNI_Mawsg8BuFcqzxV5o-S0V4F4KsgFWA; _ga_2EN3JTP1R7=GS1.1.1626845167.26.1.1626845724.0
//        origin - http://localhost:8080
//        sec-fetch-user - ?1
//        accept - text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
//        sec-ch-ua - "Chromium";v="92", " Not A;Brand";v="99", "Google Chrome";v="92"
//        sec-ch-ua-mobile - ?0
//        host - localhost:8080
//        upgrade-insecure-requests - 1
//        connection - keep-alive
//        content-type - application/x-www-form-urlencoded
//        cache-control - max-age=0
//        accept-encoding - gzip, deflate, br
//        user-agent - Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36
//        sec-fetch-dest - iframe
        
        Map<String, String> details = getRequestHeadersInMap(request);
        for (String ele : details.keySet()) {
        	System.out.println(ele+" - "+ details.get(ele));
        }
        
        try {
            if (loginDB.validate(user)) {
                
                HttpSession session = request.getSession();
                session.setAttribute("UserName", username);
                response.sendRedirect("chat");
            } else {
            	response.sendRedirect("error.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
//        if (details.get("sec-fetch-dest").equals("document")) {
//	        
//        }else {
//        	response.sendRedirect("error_iframe.jsp");
//        }
    }
    
   
    
    private Map<String, String> getRequestHeadersInMap(HttpServletRequest request) {

        Map<String, String> result = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            
            result.put(key, value);
        }

        return result;
    }

	

	
}