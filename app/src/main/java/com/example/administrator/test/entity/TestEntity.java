package com.example.administrator.test.entity;

public class TestEntity {
    private String name;
    private long id;
    private int type;

    public TestEntity(String name) {
        type = 0;
        this.name = name;
    }

    public TestEntity(String name,int type) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
