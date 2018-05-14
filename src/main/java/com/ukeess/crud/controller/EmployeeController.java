package com.ukeess.crud.controller;

import com.ukeess.crud.controller.dto.DepartmentsResponse;
import com.ukeess.crud.controller.dto.EmployeeDto;
import com.ukeess.crud.entity.Department;
import com.ukeess.crud.entity.Employee;
import com.ukeess.crud.service.DepartmentService;
import com.ukeess.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    private DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService
            , DepartmentService departmentService) {

        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String getAllEmployees(ModelMap modelMap) {
        List<EmployeeDto> employeeDtos = getEmployeeDtos();
        modelMap.addAttribute("employees", employeeDtos);
        return "index";
    }

    @GetMapping("/departments")
    @ResponseBody
    public List<DepartmentsResponse> getDepartmentNames() {
        List<Department> departments = departmentService.findAll();

        return departments
                .stream()
                .map(e -> new DepartmentsResponse(e.getName()))
                .collect(Collectors.toList());
    }

    @PostMapping("/insert")
    @ResponseBody
    public HttpStatus insert(EmployeeInsertRequest request) {
        employeeService.save(createEmployeeFromTheRequest(request));
        return HttpStatus.CREATED;
    }

    @PostMapping("/delete")
    @ResponseBody
    public HttpStatus delete(@RequestParam("empId") Long id) {
        employeeService.delete(id);
        return HttpStatus.NO_CONTENT;
    }

    @GetMapping("/update")
    public String getUpdatePage(@RequestParam("empId") Long id
            , ModelMap modelMap) {

        Employee employee = employeeService.findOne(id);
        modelMap.addAttribute("employee", createDtoForUpdatePage(employee));
        return "edit";
    }

    @PostMapping("/update")
    @ResponseBody
    public HttpStatus update(EmployeeUpdateRequest request) {
        Employee employee = employeeService.findOne(request.getEmpId());
        employeeService.save(updateEmployeeFromTheRequest(request, employee));
        return HttpStatus.OK;
    }

    @GetMapping("/view")
    public String viewEmployee(@RequestParam("empId") Long empId
            , ModelMap modelMap) {
        Employee employee = employeeService.findOne(empId);
        EmployeeDto employeeDto = new EmployeeDto(employee.getId(), employee.getName(), employee.isActive(), employee.getDepartment().getName());
        modelMap.addAttribute("employee", employeeDto);
        return "view";
    }

    private List<EmployeeDto> getEmployeeDtos() {
        List<Employee> employees = employeeService.findAll();

        //TODO fix null pointer in department section
        return employees.stream().map(e -> new EmployeeDto(e.getId()
                , e.getName()
                , e.isActive()
                , e.getDepartment().getName()))
                .collect(Collectors.toList());
    }

    private Employee createEmployeeFromTheRequest(EmployeeInsertRequest request) {
        Employee employee = new Employee();
        employee.setActive(request.isActive());
        employee.setName(request.getEmpName());
        Department department = findDepartmentByName(request.getDepartmentName());
        department.getEmployees().add(employee);
        employee.setDepartment(department);
        return employee;
    }

    private Employee updateEmployeeFromTheRequest(EmployeeUpdateRequest request, Employee employee) {
        employee.setName(request.getName());
        employee.setActive(Boolean.valueOf(request.getActive()));
        Department department = findDepartmentByName(request.getDepartmentName());
        department.getEmployees().add(employee);
        employee.setDepartment(department);
        return employee;
    }

    private EmployeeDto createDtoForUpdatePage(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto(employee.getId(), employee.getName(), employee.isActive(), employee.getDepartment().getName());
        return employeeDto;
    }

    private Department findDepartmentByName(String departmentName) {
        Department department = departmentService.findByName(departmentName);
        return department;
    }
}