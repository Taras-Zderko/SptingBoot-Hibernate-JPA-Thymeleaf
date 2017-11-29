package com.example.zderko.repository;

import com.example.zderko.model.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DepartmentRepository extends CrudRepository<Department, Integer> {

}
