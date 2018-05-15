package com.ukeess.crud.web.request;

public class EmployeeUpdateRequest {

    private Long empId;

    private String empName;

    private String departmentName;

    private String active;

    public Long getEmpId() {
        return empId;
    }

    public String getName() {
        return empName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public void setName(String name) {
        this.empName = name;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}