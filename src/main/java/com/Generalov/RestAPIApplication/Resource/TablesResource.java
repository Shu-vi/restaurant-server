package com.Generalov.RestAPIApplication.Resource;

import com.Generalov.RestAPIApplication.Entity.TablesEntity;

public class TablesResource extends BaseResource{
    Integer id;
    Integer table;
    Boolean isFree;

    TablesResource(){}

    public TablesResource(TablesEntity entity){
        id = entity.getId();
        table = entity.getTable();
        isFree = entity.getFree();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public TablesEntity toEntity(){
        return new TablesEntity(id, table, isFree);
    }
}
