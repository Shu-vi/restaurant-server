package com.Generalov.RestAPIApplication.Entity;

public class PostEntity extends BaseEntity{
    String title;


    public PostEntity(Integer id, String title) {
        super(id);
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
