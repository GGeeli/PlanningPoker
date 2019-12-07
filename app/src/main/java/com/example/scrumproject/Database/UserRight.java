package com.example.scrumproject.Database;

public class UserRight {

    boolean isAdmin;

    public UserRight() {
    }

    public UserRight(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
