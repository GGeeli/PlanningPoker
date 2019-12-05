package com.example.scrumproject;

import java.util.List;

public class Groups {
    String id;
    String name;
    Users user;

    private Groups(){}

    public Groups(String id,String name, Users user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public String getId() {
        return id;
    }
    public String getName(){return name;}
    public Users getUsers() {
        return user;
    }
}
