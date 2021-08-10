package edu.mygdx.model;

import java.sql.Timestamp;

public class User {
    private Long uid;
    private String username;
    private String password;
    private Long role;
    private Long score; // game highest score
    private Timestamp regTime;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Timestamp getRegTime() {
        return regTime;
    }

    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

}
