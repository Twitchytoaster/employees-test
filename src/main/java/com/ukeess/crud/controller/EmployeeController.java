package com.ukeess.crud.controller;

import com.ukeess.crud.controller.dto.DepartmentsResponse;
import com.ukeess.crud.controller.dto.EmployeeDto;
import com.ukeess.crud.controller.pagination.PaginationManager;
import com.ukeess.crud.entity.Department;
import com.ukeess.crud.entity.Employee;
import com.ukeess.crud.repository.EmployeeRepository;
import com.ukeess.crud.service.DepartmentService;
import com.ukeess.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    private DepartmentService departmentService;

    private PaginationManager paginationManager;

    @Autowired
    public EmployeeController(EmployeeService employeeService
            , DepartmentService departmentService
            , PaginationManager paginationManager) {

        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.paginationManager = paginationManager;
    }

    @GetMapping("/")
    public String getMainPage() {
        return "redirect:/employees";
    }

    @GetMapping("/employees")
    public String getAllEmployees(ModelMap modelMap, @PageableDefault(size = 3, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Employee> page = employeeService.findAll(pageable);
        List<EmployeeDto> employeeDtos = getEmployeeDtos(page.getContent());

        modelMap.addAttribute("leftDots", paginationManager.hasLeftDots(page));
        modelMap.addAttribute("rightDots", paginationManager.hasRightDots(page));
        modelMap.addAttribute("employees", employeeDtos);
        modelMap.addAttribute("current", page.getNumber());
        modelMap.addAttribute("pages", paginationManager.createPages(page));

        return "index";
    }

    @GetMapping("/employees/departments")
    @ResponseBody
    public List<DepartmentsResponse> getDepartmentNames() {
        List<Department> departments = departmentService.findAll();

        return departments
                .stream()
                .map(e -> new DepartmentsResponse(e.getName()))
                .collect(Collectors.toList());
    }

    @PostMapping("/employees/insert")
    @ResponseBody
    public HttpStatus insert(EmployeeInsertRequest request) {
        Employee employee = employeeService.createEmployeeFromTheRequest(request);
        Department department = departmentService.findDepartmentByName(request.getDepartmentName());
        employee.setDepartment(department);
        department.getEmployees().add(employee);
        employeeService.save(employee);
        return HttpStatus.CREATED;
    }

    @PostMapping("/employees/delete")
    @ResponseBody
    public HttpStatus delete(@RequestParam("empId") Long id) {
        employeeService.delete(id);
        return HttpStatus.NO_CONTENT;
    }

    @GetMapping("/employees/update")
    public String getUpdatePage(@RequestParam("empId") Long id
            , ModelMap modelMap) {

        Employee employee = employeeService.findOne(id);
        modelMap.addAttribute("employee", employeeService.createDtoForUpdatePage(employee));
        return "edit";
    }

    @PostMapping("/employees/update")
    @ResponseBody
    public HttpStatus update(EmployeeUpdateRequest request) {
        Employee employee = employeeService.findOne(request.getEmpId());
        employee = employeeService.updateEmployeeFromTheRequest(request, employee);
        Department department = departmentService.findDepartmentByName(request.getDepartmentName());
        department.getEmployees().add(employee);
        employee.setDepartment(department);
        employeeService.save(employee);
        return HttpStatus.OK;
    }

    @GetMapping("/employees/view")
    public String viewEmployee(@RequestParam("empId") Long empId
            , ModelMap modelMap) {
        Employee employee = employeeService.findOne(empId);
        EmployeeDto employeeDto = new EmployeeDto(employee.getId(), employee.getName(), employee.isActive(), employee.getDepartment().getName());
        modelMap.addAttribute("employee", employeeDto);
        return "view";
    }

    private List<EmployeeDto> getEmployeeDtos(List<Employee> employees) {
        //TODO fix null pointer in department section
        return employees.stream().map(e -> new EmployeeDto(e.getId()
                , e.getName()
                , e.isActive()
                , e.getDepartment().getName()))
                .collect(Collectors.toList());
    }
}