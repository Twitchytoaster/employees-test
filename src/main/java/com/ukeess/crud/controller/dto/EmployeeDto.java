package com.ukeess.crud.controller.dto;


public class EmployeeDto {

    private Long id;

    private String name;

    private boolean active;

    private String departmentName;

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String name, boolean active, String departmentName) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}