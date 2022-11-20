package com.example.instant_messaging.bean;

public class friend {
    private Integer id;
    private Integer userID1;
    private Integer userID2;

    @Override
    public String toString() {
        return "friend{" +
                "id=" + id +
                ", userID1=" + userID1 +
                ", userID2=" + userID2 +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserID1() {
        return userID1;
    }

    public void setUserID1(Integer userID1) {
        this.userID1 = userID1;
    }

    public Integer getUserID2() {
        return userID2;
    }

    public void setUserID2(Integer userID2) {
        this.userID2 = userID2;
    }
}
