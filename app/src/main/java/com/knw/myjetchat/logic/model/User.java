
package com.knw.myjetchat.logic.model;

import java.util.List;
import java.util.TimeZone;

//后续需要添加userid去做页面跳转资料的判断
public class User {
    private String name;
    private String displayName;
    private List<String> roles;
    private String status;
    private String twitter;
    private TimeZone timeZone;
    private String iconSource;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public String getIconSource() {
        return iconSource;
    }

    public void setIconSource(String iconSource) {
        this.iconSource = iconSource;
    }
}



