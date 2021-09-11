package com.whatsappweb.database;

import java.io.InputStream;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.whatsappweb.models.ChatMessage;
import com.whatsappweb.models.GroupListMessage;
import com.whatsappweb.models.GroupMessage;
import com.whatsappweb.models.Groups;
import com.whatsappweb.models.ListMessage;
import com.whatsappweb.models.Message;
import com.whatsappweb.models.Users;

public class ChatDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

   
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String SELECT_ALL_MESSAGES = "select * from messages;";
    
    private static final String UPDATE_MESSAGE_SQL = "update messages set status = ? where id = ?;";
    private static final String INSERT_MESSAGE_SQL = "INSERT INTO messages" + "  (from_username, to_username, message, time, status, type) VALUES " + " (?, ?, ?, ?, ?, ?);";
    
    private static final String INSERT_GROUP_SQL = "INSERT INTO groups" + "  (group_id, group_name) VALUES " + " (?, ?);";
    private static final String INSERT_GROUP_MEMBERS_SQL = "INSERT INTO group_members" + "  (group_id, group_member) VALUES " + " (?, ?);";
    private static final String SELECT_GROUP_ID_SQL = "select * from group_members where group_member = ?;";
    
    private static final String SELECT_GROUP_NAME_SQL = "select group_name from groups where group_id = ?;";
    private static final String SELECT_GROUP_MEMBERS_SQL = "select * from group_members where group_id = ?;";
    
    private static final String SELECT_ALL_GROUP_MESSAGES = "select * from group_messages where group_id = ?;";
    private static final String INSERT_GROUP_MESSAGE_SQL = "INSERT INTO group_messages" + "  (from_username, to_username, message, time, status, group_id, type) VALUES " + " (?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_GROUP_MESSAGE_SQL = "update group_messages set status = ? where id = ?;";
    
    private static final String STORE_AUDIO_SQL = "INSERT INTO audio_storage (filename, audio) values (?, ?)";
    

    public ChatDao() {}

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

    public boolean insertAudio(String filename, InputStream file) throws SQLException {
    	boolean done = false;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(STORE_AUDIO_SQL)) {
            preparedStatement.setString(1, filename);
            preparedStatement.setBlob(2, file);
            System.out.println(preparedStatement);
            done = preparedStatement.executeUpdate() > 0;
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return done;
    }
    

    public List<Users> selectAllUsers(String c_username) {

        List <Users> users = new ArrayList < > ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	
                String username = rs.getString("username");
                String password = rs.getString("password");
                Users user = new Users();
                if (!c_username.equals(username)) {
	                user.setUsername(username);
	                user.setPassword(password);
	                users.add(user);
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }
    
    
    public List<Groups> selectAllGroups(String c_username) {

        List <Groups> groups = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GROUP_ID_SQL);) {
        		preparedStatement.setString(1, c_username);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
           
                    String group_id = rs.getString("group_id");
                    System.out.println(group_id);
                    ArrayList<String> members = selectAllGroupMembers(group_id);
                    Groups group = new Groups();
                    group.setGroupName(getGroupName(group_id));
                    group.setGroupId(group_id);
                    group.setMembers(members);
                    groups.add(group);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
        return groups;
    }
    
    
    public ArrayList<String> selectAllGroupMembers(String g_id) {

        ArrayList<String> group_members = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GROUP_MEMBERS_SQL);) {
        		preparedStatement.setString(1, g_id);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String member = rs.getString("group_member");
                    group_members.add(member);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
        return group_members;
    }
    
    public String getGroupName(String g_id) {

        String group_name = "";
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GROUP_NAME_SQL);) {
        		preparedStatement.setString(1, g_id);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                	group_name = rs.getString("group_name");
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
        return group_name;
    }
    
    
    public void insertMessage(Message chat) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MESSAGE_SQL)) {
            preparedStatement.setString(1, chat.getFrom());
            preparedStatement.setString(2, chat.getTo());
            preparedStatement.setString(3, chat.getMessage());
            preparedStatement.setString(4, chat.getTime());
            preparedStatement.setString(5, chat.getStatus());
            preparedStatement.setString(6, chat.getType());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public boolean insertGroup(String grp_id, String name) throws SQLException {
    	boolean done = false;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GROUP_SQL)) {
            preparedStatement.setString(1, grp_id);
            preparedStatement.setString(2, name);
            System.out.println(preparedStatement);
            done = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return done;
    }
    
    public boolean insertGroupMembers(String grp_id, String name) throws SQLException {
    	boolean done = false;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GROUP_MEMBERS_SQL)) {
            preparedStatement.setString(1, grp_id);
            preparedStatement.setString(2, name);
            System.out.println(preparedStatement);
            done = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return done;
    }
    
    public List<ListMessage> getUserMessages(String username, String current_username) {

        List <ListMessage> messagesList = new ArrayList < > ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MESSAGES);) {
        	
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	 int id = rs.getInt("id");
                String to = rs.getString("to_username");
                String from = rs.getString("from_username");
                String message = rs.getString("message");
                String time = rs.getString("time");
                String type = rs.getString("type");
                
                //if (type.equals("MESSAGE")) {
                	message = decrypt(message, (from+to));
                //}
                
                String status = rs.getString("status");
                if ((to.equals(current_username) && from.equals(username)) || (to.equals(username) && from.equals(current_username))) {
	                ListMessage messageItem = new ListMessage();
	                messageItem.setId(id);
	                messageItem.setTo(to);
	                messageItem.setFrom(from);
	                messageItem.setType(type);
	                messageItem.setMessage(message);
	                try {
						Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(time);
						messageItem.setTime(new SimpleDateFormat("HH:mm aa").format(date));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
	                
	                messageItem.setStatus(status);
	                if (from.equals(current_username)) {
	                	messageItem.setPlacement("right");
	                }
	                if (to.equals(current_username)) {
	                	if (status.equals("0")) {
	                		if (updateMessage(messageItem)) {
		                		messageItem.setStatus("1");
		                	}
	                	}
	                	
	                	messageItem.setPlacement("left");
	                }
	                messagesList.add(messageItem);
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return messagesList;
    }


    public boolean updateMessage(ListMessage message) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_MESSAGE_SQL);) {
            statement.setString(1, "1");
            statement.setInt(2, message.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
    
    //Group Messages
    
    
    public void insertGroupMessage(GroupMessage chat) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GROUP_MESSAGE_SQL)) {
            preparedStatement.setString(1, chat.getFrom());
            preparedStatement.setString(2, chat.getTo());
            preparedStatement.setString(3, chat.getMessage());
            preparedStatement.setString(4, chat.getTime());
            preparedStatement.setString(5, chat.getStatus());
            preparedStatement.setString(6, chat.getGroupId());
            preparedStatement.setString(7, chat.getType());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public boolean updateGroupMessage(GroupListMessage message) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_GROUP_MESSAGE_SQL);) {
            statement.setString(1, "1");
            statement.setInt(2, message.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
    
    public List<GroupListMessage> getGroupMessages(String group_id, String current_username) {

        List <GroupListMessage> messagesList = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GROUP_MESSAGES);) {
        	preparedStatement.setString(1, group_id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	
            	int id = rs.getInt("id");
                String to = rs.getString("to_username");
                String from = rs.getString("from_username");
                String message = rs.getString("message");
                String time = rs.getString("time");
                String status = rs.getString("status");
                String g_id = rs.getString("group_id");
                String type = rs.getString("type");
                
                //if (type.equals("MESSAGE")) {
                	message = decrypt(message, (from+to));
                //}
                
                GroupListMessage messageItem = new GroupListMessage();
                messageItem.setId(id);
                messageItem.setTo(to);
                messageItem.setFrom(from);
                messageItem.setMessage(message);
                messageItem.setType(type);;
                messageItem.setGroupId(g_id);
                try {
					Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(time);
					messageItem.setTime(new SimpleDateFormat("HH:mm aa").format(date));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
                
                messageItem.setStatus(status);
                if (from.equals(current_username)) {
                	messageItem.setPlacement("right");
                }else{
                	if (status.equals("0")) {
                		if (updateGroupMessage(messageItem)) {
	                		messageItem.setStatus("1");
	                	}
                	}
                	
                	messageItem.setPlacement("left");
                }
                
                messagesList.add(messageItem);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return messagesList;
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
