package com.ukeess.crud.service;

import com.ukeess.crud.web.request.EmployeeInsertRequest;
import com.ukeess.crud.web.request.EmployeeUpdateRequest;
import com.ukeess.crud.web.dto.EmployeeDto;
import com.ukeess.crud.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    void save(Employee employee);

    void delete(Long id);

    Employee findOne(Long id);

    Page<Employee> findAll(Pageable pageable);

    Employee createEmployeeFromTheRequest(EmployeeInsertRequest request);

    Employee updateEmployeeFromTheRequest(EmployeeUpdateRequest request, Employee employee);

    EmployeeDto createDtoForUpdatePage(Employee employee);

    List<EmployeeDto> getEmployeeDtos(List<Employee> employees);

}