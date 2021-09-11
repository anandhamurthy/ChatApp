package com.whatsappweb.models;

public class GroupListMessage {
	
	int id;
	String message, to, from, status, time, placement, group_id, type;
	
	public GroupListMessage() {
		
	}
	
	public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
	
	public String getGroupId() {
        return this.group_id;
    }

    public void setGroupId(String group_id) {
        this.group_id = group_id;
    }
	
	public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public String getPlacement() {
        return this.placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
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
