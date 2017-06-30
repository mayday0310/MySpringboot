package com.mayday.redis;

/**
 * Created by Eric on 2017/6/26.
 */
public class User {

    String userName;
    String password;
    String a;
    String b;
    String c;

    public User() {
    }

    public User(String userName, String password, String a, String b, String c) {
        this.userName = userName;
        this.password = password;
        this.a = a;
        this.b = b;
        this.c = c;
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

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}
