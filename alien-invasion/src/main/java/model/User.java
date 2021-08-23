package model;

import java.sql.Timestamp;

/**
 * This is User model file for building the game.
 *
 * @author Shenquan Wang, Ran Qin
 * @version 1.0
 * @since 08/15/2021
 */
public class User {
    /**
     * This is the filed for this file.
     */
    private Long uid;
    private String username;
    private String password;
    private Long role;
    private Long score; // game highest score
    private Timestamp regTime;

    /**
     * This is the getter function for uid.
     * @return uid user id
     */
    public Long getUid() {
        return uid;
    }

    /**
     * This is the setter function for uid.
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * This is the getter function for username.
     * @return username user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * This is the setter function for username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This is the getter function for password.
     * @return password user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This is the setter function for password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This is the getter function for password.
     * @return password user password
     */
    public Long getRole() {
        return role;
    }

    /**
     * This is the setter function for password.
     */
    public void setRole(Long role) {
        this.role = role;
    }

    /**
     * This is the getter function for register time.
     * @return regTime user register time
     */
    public Timestamp getRegTime() {
        return regTime;
    }

    /**
     * This is the setter function for register time.
     */
    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }

    /**
     * This is the getter function for score.
     * @return score user score.
     */
    public Long getScore() {
        return score;
    }
    /**
     * This is the setter function for score.
     */
    public void setScore(Long score) {
        this.score = score;
    }

}
