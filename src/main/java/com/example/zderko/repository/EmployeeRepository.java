package com.example.zderko.repository;

import com.example.zderko.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    Page<Employee> findByNameContaining(String q, Pageable pageable);
    Page<Employee> findAll(Pageable pageable);

}
