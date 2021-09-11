package com.whatsappweb.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Groups implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private String groupName, groupId;
    private ArrayList<String> members;

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String grp_name) {
        this.groupName = grp_name;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    
    public ArrayList<String> getMembers() {
        return this.members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }
}