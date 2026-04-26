package com.airtel.inventory.service;

import com.airtel.inventory.model.*;
import com.airtel.inventory.repository.AssignmentRepository;
import com.airtel.inventory.repository.DeviceRepository;
import com.airtel.inventory.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssignmentService {
    
    @Autowired
    private AssignmentRepository assignmentRepository;
    
    @Autowired
    private DeviceRepository deviceRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Transactional
    public Assignment issueDevice(Long deviceId, Long employeeId, String purpose, String notes) {
        Device device = deviceRepository.findById(deviceId)
            .orElseThrow(() -> new RuntimeException("Device not found"));
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        
        if (!device.isAvailable()) {
            throw new RuntimeException("Device is already assigned");
        }
        
        Assignment assignment = new Assignment();
        assignment.setDevice(device);
        assignment.setEmployee(employee);
        assignment.setIssueDate(LocalDateTime.now());
        assignment.setConditionAtIssue(device.getCondition());
        assignment.setPurpose(purpose);
        assignment.setNotes(notes);
        assignment.setReturned(false);
        
        device.setAvailable(false);
        deviceRepository.save(device);
        
        return assignmentRepository.save(assignment);
    }
    
    @Transactional
    public Assignment returnDevice(Long assignmentId, DeviceCondition returnCondition) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
            .orElseThrow(() -> new RuntimeException("Assignment not found"));
        
        assignment.setReturnDate(LocalDateTime.now());
        assignment.setConditionAtReturn(returnCondition);
        assignment.setReturned(true);
        
        Device device = assignment.getDevice();
        device.setCondition(returnCondition);
        device.setAvailable(true);
        deviceRepository.save(device);
        
        return assignmentRepository.save(assignment);
    }
    
    public List<Assignment> getActiveAssignments() {
        return assignmentRepository.findByIsReturnedFalse();
    }
    
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }
}