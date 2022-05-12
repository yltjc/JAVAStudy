package com.qdc.demoeurekaprovider1.pojo;

public class User {
    private String usermane;
    private String password;
    private String name;
    public User(){}

    public User(String usermane, String password, String name) {
        this.usermane = usermane;
        this.password = password;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "usermane='" + usermane + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getUsermane() {
        return usermane;
    }

    public void setUsermane(String usermane) {
        this.usermane = usermane;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
