package com.airtel.inventory.repository;

import com.airtel.inventory.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmployeeId(String employeeId);
    List<Employee> findByDepartment(String department);
    List<Employee> findByFullNameContaining(String name);
}