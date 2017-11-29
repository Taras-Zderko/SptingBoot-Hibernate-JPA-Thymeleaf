package com.example.zderko.service;

import com.example.zderko.model.Department;
import com.example.zderko.model.Employee;
import com.example.zderko.repository.DepartmentRepository;
import com.example.zderko.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Page<Employee> findAllEmp(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> search(String q,Pageable page) {
        return employeeRepository.findByNameContaining(q, page);
    }

    @Override
    public Employee findOne(int id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public void save(Employee contact) {
        employeeRepository.save(contact);
    }

    @Override
    public void delete(int id) {
        employeeRepository.delete(id);
    }
}
