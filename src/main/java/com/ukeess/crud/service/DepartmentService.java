package com.ukeess.crud.service;

import com.ukeess.crud.entity.Department;

import java.util.List;

public interface DepartmentService {

    void save(Department department);

    void delete(Long id);

    Department findOne(Long id);

    Department findByName(String name);

    List<Department> findAll();

    Department findDepartmentByName(String departmentName);
}