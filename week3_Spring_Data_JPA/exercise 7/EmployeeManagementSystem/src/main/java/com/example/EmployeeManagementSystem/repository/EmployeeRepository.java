package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name%")
    Page<Employee> findByNameContaining(@Param("name") String name, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.department.name LIKE %:departmentName%")
    Page<Employee> findByDepartmentNameContaining(@Param("departmentName") String departmentName, Pageable pageable);
}
