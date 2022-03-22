package com.Generalov.RestAPIApplication.Entity;

public class TablesEntity extends BaseEntity{
    private Integer table;
    private Boolean isFree;

    public TablesEntity(Integer id, Integer tables, Boolean isFree) {
        super(id);
        this.table = tables;
        this.isFree = isFree;
    }

    public Integer getTable() {
        return table;
    }

    public void setTable(Integer table) {
        this.table = table;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }
}
