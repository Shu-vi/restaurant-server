package com.Generalov.RestAPIApplication.Entity;

public class EmployeeEntity extends BaseEntity{
    private String name;
    private String lastname;
    private String passportSeAndNu;
    private Integer post;
    private Integer salary;
    private Integer age;

    public EmployeeEntity(Integer id, String name, String lastname, String passportSeAndNu, Integer post, Integer salary, Integer age) {
        super(id);
        this.name = name;
        this.lastname = lastname;
        this.passportSeAndNu = passportSeAndNu;
        this.post = post;
        this.salary = salary;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassportSeAndNu(String passportSeAndNu) {
        this.passportSeAndNu = passportSeAndNu;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassportSeAndNu() {
        return passportSeAndNu;
    }

    public Integer getPost() {
        return post;
    }

    public Integer getSalary() {
        return salary;
    }

    public Integer getAge() {
        return age;
    }
}
