package edu.alieninvasion.backend.bean;
/**
 * <h1>RespBean</h1>
 * The entity class storing response content to frontend and client
 *
 * @author  Ran Qin and Shenquan Wang
 * @version 1.0
 * @since   2021-07-16
 */
public class RespBean {
    private String status;  // load status of data
    private String msg;     // load result of data
    private String username;    // name of current user
    private Long uid;   // id of current user
    private Long role;  // role of current user
    private Long score; // highest score of current user

    public RespBean(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public RespBean(String status, String msg, Long uid, Long role, String username, Long score) {
        this.username = username;
        this.status = status;
        this.msg = msg;
        this.uid = uid;
        this.role = role;
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
