package com.Generalov.RestAPIApplication.Resource;


import com.Generalov.RestAPIApplication.Entity.PostEntity;

public class PostResource extends BaseResource{
    Integer id;
    String title;

    public PostResource(){}

    public PostResource(PostEntity entity) {
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

    public PostEntity toEntity(){
        return new PostEntity(id, title);
    }
}
