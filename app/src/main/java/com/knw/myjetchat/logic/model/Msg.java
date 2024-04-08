package com.knw.myjetchat.logic.model;

import java.util.Date;

//后续需要完善跳转个人信息页面，同时profile也要加上每个人的item
public class Msg {
    private String content;
    private int type;
    private int iconId;
    private Date timestamp;
    private String senderName;

    private Integer imgSourceId;


    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;

    public Msg(String content, int type, int iconId, Date timestamp, String senderName,Integer imgSourceId) {
        this.content = content;
        this.type = type;
        this.iconId = iconId;
        this.timestamp = timestamp;
        this.senderName = senderName;
        this.imgSourceId = imgSourceId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Integer getImgSourceId(){return imgSourceId;}
    public void setImgSourceId(Integer imgSourceId){this.imgSourceId=imgSourceId;}


    }
