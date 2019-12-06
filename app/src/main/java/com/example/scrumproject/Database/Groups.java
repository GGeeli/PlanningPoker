package com.example.scrumproject.Database;


import java.util.List;

public class Groups {
    private String id;
    private String name;
    private List<Users> user;

    private Groups() {
    }

    public Groups(String id, String name, List<Users> user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Users> getUser() {
        return user;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(List<Users> user) {
        this.user = user;
    }
}
