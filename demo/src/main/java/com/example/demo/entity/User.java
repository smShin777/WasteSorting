package com.example.demo.entity;

public class User {
    private String id;
    private String password;
    private boolean isAdmin;

    public User(String id, String password, boolean isAdmin)
    {
        this.id = id;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getId()
    {
        return id;
    }

    public String getPassword()
    {
        return password;
    }

    public boolean isAdmin()
    {
        return isAdmin;
    }
}
