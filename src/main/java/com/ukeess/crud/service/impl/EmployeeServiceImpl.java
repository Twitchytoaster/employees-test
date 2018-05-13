package com.ukeess.crud.service.impl;

import com.ukeess.crud.entity.Employee;
import com.ukeess.crud.repository.EmployeeRepository;
import com.ukeess.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee findOne(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}