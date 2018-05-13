package com.ukeess.crud.controller;

import com.ukeess.crud.controller.dto.EmployeeDto;
import com.ukeess.crud.entity.Employee;
import com.ukeess.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return getEmployeeDtos();
    }

    @PostMapping("/insert")
    @ResponseBody
    public String insert(EmployeeInsertRequest request) {
        return request.getEmpName() + " " + request.isActive() + " " + request.getDepartmentName();
    }

    private List<EmployeeDto> getEmployeeDtos() {
        List<Employee> employees = employeeService.findAll();

        return employees.stream().map(e -> new EmployeeDto(e.getId()
                , e.getName()
                , e.isActive()
                , e.getDepartment().getName()))
                .collect(Collectors.toList());
    }
}