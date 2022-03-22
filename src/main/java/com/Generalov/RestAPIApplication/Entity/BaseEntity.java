package com.Generalov.RestAPIApplication.Entity;

public abstract class BaseEntity {
    private Integer id;

    public BaseEntity(Integer id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
