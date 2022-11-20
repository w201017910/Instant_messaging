package com.example.instant_messaging.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
public class Message {
    public int id;
    //发送者name
    public String from;



    //接收者name
    public String to;
    //发送的文本
    public String message;
    //发送时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return message;
    }

    public void setText(String text) {
        this.message = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
