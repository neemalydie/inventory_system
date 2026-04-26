package com.airtel.inventory.repository;

import com.airtel.inventory.model.Assignment;
import com.airtel.inventory.model.Device;
import com.airtel.inventory.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByEmployeeAndIsReturnedFalse(Employee employee);
    List<Assignment> findByDeviceAndIsReturnedFalse(Device device);
    List<Assignment> findByIsReturnedFalse();
}