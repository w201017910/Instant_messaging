package com.example.instant_messaging.bean;

public class User {
    private Integer ID;
    private String Name;
    private String password;
    private String img;

    public Integer getUserID() {
        return ID;
    }

    public void setUserID(Integer userID) {
        this.ID = userID;
    }

    public void setUserName(String userName) {
        this.Name = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUserName() {
        return Name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + ID +
                ", userName='" + Name + '\'' +
                ", password='" + password + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public String getImg() {
        return img;
    }
}
