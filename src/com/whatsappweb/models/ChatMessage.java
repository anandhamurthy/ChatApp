package com.whatsappweb.models;

public class ChatMessage {
	private MessageType messageType;
	private String message, frontmessage, to, from, type;

	public void setMessageType(MessageType v) { this.messageType = v; }
	public MessageType getMessageType() { return messageType; }
	public void setMessage(String v) { this.message = v; }
	public String getMessage() { return this.message; }
	
	
	public String getFrontmessage() {
        return this.frontmessage;
    }

    public void setFrontmessage(String frontmessage) {
        this.frontmessage = frontmessage;
    }
	
	
	public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
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
}
