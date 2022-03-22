package com.Generalov.RestAPIApplication.Entity;

public class MenuEntity extends BaseEntity {
    private String title;

    public MenuEntity(Integer id, String title) {
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
