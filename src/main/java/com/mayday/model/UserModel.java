package com.mayday.model;

import java.util.Date;

/**
 * 用户模型层
 */

public class UserModel {

    private String id;  //用户id
    private String userName;  //用户名
    private String password;  //用户密码
    private Date createTime;  //创建时间
    private String lastLoginIp;  //最后登录IP
    private String email;  //用户绑定邮箱
    private int status;  //用户状态
    private int level;  //用户等级

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public UserModel() {
    }

    public UserModel(String id, String userName, String password, Date createTime, String lastLoginIp, String email, int status, int level) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.createTime = createTime;
        this.lastLoginIp = lastLoginIp;
        this.email = email;
        this.status = status;
        this.level = level;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", level=" + level +
                '}';
    }
}
