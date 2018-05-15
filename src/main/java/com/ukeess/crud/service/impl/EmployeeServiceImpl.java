package com.ukeess.crud.service.impl;

import com.ukeess.crud.controller.EmployeeInsertRequest;
import com.ukeess.crud.controller.EmployeeUpdateRequest;
import com.ukeess.crud.controller.dto.EmployeeDto;
import com.ukeess.crud.entity.Department;
import com.ukeess.crud.entity.Employee;
import com.ukeess.crud.repository.EmployeeRepository;
import com.ukeess.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee createEmployeeFromTheRequest(EmployeeInsertRequest request) {
        Employee employee = new Employee();
        employee.setActive(request.isActive());
        employee.setName(request.getEmpName());
        return employee;
    }

    @Override
    public Employee updateEmployeeFromTheRequest(EmployeeUpdateRequest request, Employee employee) {
        employee.setName(request.getName());
        employee.setActive(Boolean.valueOf(request.getActive()));
        return employee;
    }

    @Override
    public EmployeeDto createDtoForUpdatePage(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto(employee.getId(), employee.getName(), employee.isActive(), employee.getDepartment().getName());
        return employeeDto;
    }

    @Override
    public List<EmployeeDto> getEmployeeDtos(List<Employee> employees) {
        return employees.stream().map(e -> {
            EmployeeDto dto = new EmployeeDto();
            dto.setId(e.getId());
            dto.setName(e.getName());
            dto.setActive(e.isActive());
            String departmentName = e.getDepartment() != null ? e.getDepartment().getName() : null;
            dto.setDepartmentName(departmentName);
            return dto;
        }).collect(Collectors.toList());
    }
}