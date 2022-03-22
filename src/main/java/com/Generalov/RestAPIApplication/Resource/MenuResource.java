package com.Generalov.RestAPIApplication.Resource;

import com.Generalov.RestAPIApplication.Entity.MenuEntity;

public class MenuResource extends BaseResource{
    private Integer id;
    private String title;

    public MenuResource(){}

    public MenuResource(MenuEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MenuEntity toEntity(){
        return new MenuEntity(this.id, this.title);
    }
}
