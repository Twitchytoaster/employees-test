package com.ukeess.crud.service.impl;

import com.ukeess.crud.entity.Department;
import com.ukeess.crud.repository.DepartmentRepository;
import com.ukeess.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department findOne(Long id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    public Department findByName(String name) {
        return departmentRepository.findByName(name);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findDepartmentByName(String departmentName) {
        return departmentRepository.findByName(departmentName);
    }
}