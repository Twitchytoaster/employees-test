package com.ukeess.crud.web.dto;

public class DepartmentsResponse {

    private String name;

    public DepartmentsResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}