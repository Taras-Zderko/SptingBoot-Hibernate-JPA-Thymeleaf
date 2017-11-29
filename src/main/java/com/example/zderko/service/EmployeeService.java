package com.example.zderko.service;

import com.example.zderko.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface  EmployeeService {

    Page<Employee> findAllEmp(Pageable pageable);
    Page<Employee> search(String q, Pageable pageable);
    Employee findOne(int id);
    void save(Employee contact);
    void delete(int id);
}
