package com.knw.myjetchat.logic.model;

public class Group {
    private String groupName;
    private Integer groupMemberCount;

    public Group(String groupName, Integer groupMemberCount) {
        this.groupName = groupName;
        this.groupMemberCount = groupMemberCount;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGroupMemberCount() {
        return groupMemberCount;
    }

    public void setGroupMemberCount(Integer groupMemberCount) {
        this.groupMemberCount = groupMemberCount;
    }
}
