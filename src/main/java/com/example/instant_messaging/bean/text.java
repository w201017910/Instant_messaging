package com.example.instant_messaging.bean;

public class text {
    private boolean judge;
    private String message;
    public User user;

    @Override
    public String toString() {
        return "text{" +
                "judge=" + judge +
                ", message='" + message + '\'' +
                '}';
    }

    public boolean isJudge() {
        return judge;
    }

    public void setJudge(boolean judge) {
        this.judge = judge;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
