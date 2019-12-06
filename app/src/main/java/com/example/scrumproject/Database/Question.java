package com.example.scrumproject.Database;

public class Question {
    String id;
    String name;

    int count;
    double averange;

    int timer;

    public Question(){}

    public Question(String id, String name,  int count,double averange,int timer) {
        this.id = id;
        this.name = name;
        this.averange = averange;
        this.count = count;
        this.timer = timer;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public double getAverange() {
        return averange;
    }

    public int getTimer(){return timer;}
}
