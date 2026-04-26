package com.airtel.inventory.service;

import com.airtel.inventory.model.Employee;
import com.airtel.inventory.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    public Employee registerEmployee(Employee employee) {
        employee.setCreatedAt(LocalDateTime.now());
        return employeeRepository.save(employee);
    }
    
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
    
    public Employee getEmployeeByEmployeeId(String employeeId) {
        return employeeRepository.findByEmployeeId(employeeId);
    }
    
    // ========== UPDATE EMPLOYEE ==========
    public Employee updateEmployee(Employee employee) {
        Employee existingEmployee = getEmployeeById(employee.getId());
        existingEmployee.setEmployeeId(employee.getEmployeeId());
        existingEmployee.setFullName(employee.getFullName());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        return employeeRepository.save(existingEmployee);
    }

    // ========== DELETE EMPLOYEE ==========
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        // Check if employee has active assignments
        if (!employee.getAssignments().isEmpty()) {
            boolean hasActive = employee.getAssignments().stream().anyMatch(a -> !a.isReturned());
            if (hasActive) {
                throw new RuntimeException("Cannot delete employee with active device assignments");
            }
        }
        employeeRepository.deleteById(id);
    }
}