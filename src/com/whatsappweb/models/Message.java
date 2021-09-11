package com.whatsappweb.models;

public class Message {
	
	String message, to, from, status, time, type;
	
	public Message() {
		
	}
	
	public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
	
	public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    
    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    
    
    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
 	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	
}
