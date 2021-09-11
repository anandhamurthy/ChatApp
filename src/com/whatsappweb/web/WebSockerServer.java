package com.whatsappweb.web;

import java.util.logging.Logger;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import org.codehaus.jackson.map.ObjectMapper;

import com.whatsappweb.database.ChatDao;
import com.whatsappweb.models.ChatMessage;
import com.whatsappweb.models.GroupMessage;
import com.whatsappweb.models.Message;
import com.whatsappweb.models.MessageType;


@ServerEndpoint(value = "/chat")
public class WebSockerServer {
	
	private ChatDao chatDao = new ChatDao();

	private Logger log = Logger.getLogger(WebSockerServer.class.getSimpleName());    
    
    static Set<Session> chatRoomUsers = Collections.synchronizedSet(new HashSet<Session>());
    static Map<String,Session> chatRoomUsersMap = Collections.synchronizedMap(new HashMap<String,Session>());
    
	@OnOpen
    public void onOpen(final Session session, EndpointConfig config){
		chatRoomUsers.add(session);
        System.out.println("Connection Opened");
    }
     
	@OnClose
    public void onClose(Session session, CloseReason reason) {
		chatRoomUsers.remove(session);
		System.out.println("Connection Closed");
    }

    @OnError
    public void onError(Session session, Throwable ex) { ex.printStackTrace(); }
    
    
//    @OnMessage
//    public void onMessage(byte[] b, boolean last, Session session) {
//    
//    	Map<String, Object> properties = session.getUserProperties();
//    	ByteBuffer byteBuffer = ByteBuffer.wrap(b);
//    	
//    	String to_username = properties.get("to").toString();
//        String from_username = properties.get("from").toString();
//        
//        System.out.println(to_username+from_username);
//        
//        Session senderSession = chatRoomUsersMap.get(to_username+from_username);
//        
//        if (chatRoomUsersMap.containsKey(from_username+to_username)) {
//        	
//        	Session receiverSession = chatRoomUsersMap.get(from_username+to_username);
//        	
//        	System.out.println(to_username+from_username);
//        	
//        	String tim = new SimpleDateFormat("HH:mm aa").format(Calendar.getInstance().getTime());
//			//receiverSession.getBasicRemote().sendText("Mess"+"$"+from_username+"$"+tim+"$"+"IMAGE");
//			
//			try {
//				receiverSession.getBasicRemote().sendBinary(byteBuffer);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			
//			//senderSession.getBasicRemote().sendText("Mess"+"$"+from_username+"$"+tim+"$"+"IMAGE");
//			try {
//				senderSession.getBasicRemote().sendBinary(byteBuffer);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//        }else {
//        	
//        	
//        	
//        	String time = new SimpleDateFormat("HH:mm aa").format(Calendar.getInstance().getTime());
//			//senderSession.getBasicRemote().sendText("Mess"+"$"+from_username+"$"+time+"$"+"IMAGE");
//			try {
//				senderSession.getBasicRemote().sendBinary(byteBuffer);;
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        	
//        	
//        }
//	
//		//byteBuffer.clear();
//        
//    }
    
   
     
    @OnMessage
    public void onMessage(byte[] b, boolean last, Session session) {
    
    	Map<String, Object> properties = session.getUserProperties();
    	ByteBuffer byteBuffer = ByteBuffer.wrap(b);
    	
    	String to_username = properties.get("to").toString();
        String from_username = properties.get("from").toString();
        
        System.out.println(to_username+from_username);
        
        Session senderSession = chatRoomUsersMap.get(to_username+from_username);
        
        if (chatRoomUsersMap.containsKey(from_username+to_username)) {
        	
        	Session receiverSession = chatRoomUsersMap.get(from_username+to_username);
        	
        	System.out.println(to_username+from_username);
        	
        	String tim = new SimpleDateFormat("HH:mm aa").format(Calendar.getInstance().getTime());
			//receiverSession.getBasicRemote().sendText("Mess"+"$"+from_username+"$"+tim+"$"+"IMAGE");
			
			try {
				receiverSession.getBasicRemote().sendBinary(byteBuffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			//senderSession.getBasicRemote().sendText("Mess"+"$"+from_username+"$"+tim+"$"+"IMAGE");
			try {
				senderSession.getBasicRemote().sendBinary(byteBuffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }else {
        	
        	
        	
        	String time = new SimpleDateFormat("HH:mm aa").format(Calendar.getInstance().getTime());
			//senderSession.getBasicRemote().sendText("Mess"+"$"+from_username+"$"+time+"$"+"IMAGE");
			try {
				senderSession.getBasicRemote().sendBinary(byteBuffer);;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
        }
	
        
		
		
		//byteBuffer.clear();
        
    }
    
    
    @OnMessage
    public void onMessage(final Session session, final String messageJson) {
        ObjectMapper mapper = new ObjectMapper();
        ChatMessage chatMessage = null;
        try {
            chatMessage = mapper.readValue(messageJson, ChatMessage.class);
        } catch (IOException e) {
            String message = "Badly formatted message";
            try {
                session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, message));
                System.out.print(message+"here");
            } catch (IOException ex) { log.severe(ex.getMessage());
            System.out.print(message+"here");}
        } ;

        Map<String, Object> properties = session.getUserProperties();
        if (chatMessage.getMessageType() == MessageType.LOGIN) {
        	
        	if (chatMessage.getType().equals("group")) {
        		
        		String to_username = chatMessage.getTo();
	            String from_username = chatMessage.getFrom();
	            System.out.println(chatMessage.getType());
	            chatRoomUsersMap.put(to_username+from_username, session);
	            
        	}else {
        	
	        	String to_username = chatMessage.getTo();
	            String from_username = chatMessage.getFrom();
	            
	            properties.put("to", to_username);
	            properties.put("from", from_username);
	        
	        	chatRoomUsersMap.put(to_username+from_username, session);
        	}
            
        } else {
        	
        	if (chatMessage.getType().equals("group")) {
        		
        		Session senderSession = chatRoomUsersMap.get(chatMessage.getTo()+chatMessage.getFrom());
        		
        		System.out.println(chatMessage.getType());
        		
        		ArrayList<String> members = chatDao.selectAllGroupMembers(chatMessage.getTo());
        		
        		
        		
        		for (String member : members) {
        			
        			System.out.println(member);
        			if (!member.equals(chatMessage.getFrom()) && chatRoomUsersMap.containsKey(chatMessage.getTo()+member)) {
        				try {
        	            	Session receiverSession = chatRoomUsersMap.get(chatMessage.getTo()+member);
        	            	String time = new SimpleDateFormat("HH:mm aa").format(Calendar.getInstance().getTime());
        					receiverSession.getBasicRemote().sendText(chatMessage.getMessage()+"$"+chatMessage.getFrom()+"$"+time+"$"+chatMessage.getMessageType().toString());
        			        
        	            	}catch (IOException e) {
        	    				e.printStackTrace();
        	    			}
        			}
        		}
        		
        		try {
        			String time = new SimpleDateFormat("HH:mm aa").format(Calendar.getInstance().getTime());
					senderSession.getBasicRemote().sendText(chatMessage.getMessage()+"$"+chatMessage.getFrom()+"$"+time+"$"+chatMessage.getMessageType().toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
				GroupMessage message = new GroupMessage();
		        message.setFrom(chatMessage.getFrom());
		        message.setMessage(chatMessage.getMessage());
		        message.setTo(chatMessage.getTo());
		        message.setType(chatMessage.getMessageType().toString());
		        String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		        message.setTime(time);
		        message.setStatus("0");
		        message.setGroupId(chatMessage.getTo());
		        try {
					chatDao.insertGroupMessage(message);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        	}else {
            
	            Session senderSession = chatRoomUsersMap.get(chatMessage.getTo()+chatMessage.getFrom());
	            
	            if (chatRoomUsersMap.containsKey(chatMessage.getFrom()+chatMessage.getTo())) {
	            	
	            	try {
	            	Session receiverSession = chatRoomUsersMap.get(chatMessage.getFrom()+chatMessage.getTo());
	            	
	            	String tim = new SimpleDateFormat("HH:mm aa").format(Calendar.getInstance().getTime());
					receiverSession.getBasicRemote().sendText(chatMessage.getFrontmessage()+"$"+chatMessage.getFrom()+"$"+tim+"$"+chatMessage.getMessageType().toString());
					
					try {
						senderSession.getBasicRemote().sendText(chatMessage.getFrontmessage()+"$"+chatMessage.getFrom()+"$"+tim+"$"+chatMessage.getMessageType().toString());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Message message = new Message();
			        message.setFrom(chatMessage.getFrom());
			        //if (chatMessage.getMessageType().toString().equals("MESSAGE"))
			        	message.setMessage(encrypt(chatMessage.getMessage(),(chatMessage.getFrom()+chatMessage.getTo())));
			        //else
			        	//message.setMessage(chatMessage.getMessage());
			        message.setTo(chatMessage.getTo());
			        message.setType(chatMessage.getMessageType().toString());
			        String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
			        message.setTime(time);
			        message.setStatus("0");
			        try {
						chatDao.insertMessage(message);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        
	            	}catch (IOException e) {
	    				e.printStackTrace();
	    			}
	            }else {
	            	
	            	try {
	            		String time = new SimpleDateFormat("HH:mm aa").format(Calendar.getInstance().getTime());
						senderSession.getBasicRemote().sendText(chatMessage.getFrontmessage()+"$"+chatMessage.getFrom()+"$"+time+"$"+chatMessage.getMessageType().toString());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            	
	            	Message message = new Message();
			        message.setFrom(chatMessage.getFrom());
			        //if (chatMessage.getMessageType().toString().equals("MESSAGE"))
			        	message.setMessage(encrypt(chatMessage.getMessage(),(chatMessage.getFrom()+chatMessage.getTo())));
			        //else
			        	//message.setMessage(chatMessage.getMessage());
			        message.setTo(chatMessage.getTo());
			        message.setType(chatMessage.getMessageType().toString());
			        String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
			        message.setTime(time);
			        message.setStatus("0");
			        try {
						chatDao.insertMessage(message);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
        	}
            
			
        }
    }
    
    
    private String encrypt(String message, String key) {
    	try {
                byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
                String SALT = "whatsappweb";
                IvParameterSpec ivspec = new IvParameterSpec(iv);
                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                
                KeySpec spec = new PBEKeySpec(key.toCharArray(), SALT.getBytes(),65536, 256);
                SecretKey tmp = factory.generateSecret(spec);
                SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
      
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
                return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes(StandardCharsets.UTF_8)));
            }
            catch (Exception e) {
                System.out.println("Error while encrypting: " + e.toString());
            }
            return null;
     }
    
    
   

 
}
