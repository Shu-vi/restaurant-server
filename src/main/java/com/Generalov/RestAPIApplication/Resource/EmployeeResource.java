package com.Generalov.RestAPIApplication.Resource;

import com.Generalov.RestAPIApplication.Entity.EmployeeEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

public class EmployeeResource extends BaseResource{
    private Integer id;
    private String name;
    private String lastname;
    private String passportSeAndNu;
    private Integer post;
    private Integer salary;
    private Integer age;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PostResource postResource;

    public EmployeeResource() {}

    public EmployeeResource(EmployeeEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.lastname = entity.getLastname();
        this.passportSeAndNu = entity.getPassportSeAndNu();
        this.post = entity.getPost();
        this.salary = entity.getSalary();
        this.age = entity.getAge();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassportSeAndNu() {
        return passportSeAndNu;
    }

    public void setPassportSeAndNu(String passportSeAndNu) {
        this.passportSeAndNu = passportSeAndNu;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public PostResource getPostResource() {
        return postResource;
    }

    public void setPostResource(PostResource postResource) {
        this.postResource = postResource;
    }

    public EmployeeEntity toEntity(){
        return new EmployeeEntity(
                id, name, lastname, passportSeAndNu, post, salary, age
        );
    }
}
