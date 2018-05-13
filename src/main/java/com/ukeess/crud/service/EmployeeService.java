package com.ukeess.crud.service;

import com.ukeess.crud.entity.Employee;

import java.util.List;

public interface EmployeeService {

    void save(Employee employee);

    void delete(Long id);

    Employee findOne(Long id);

    List<Employee> findAll();
}