package com.airtel.inventory.controller;

import com.airtel.inventory.model.Assignment;
import com.airtel.inventory.model.DeviceCondition;
import com.airtel.inventory.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    
    @Autowired
    private AssignmentService assignmentService;
    
    @PostMapping("/issue")
    public ResponseEntity<Assignment> issueDevice(@RequestBody Map<String, Object> request) {
        Long deviceId = Long.valueOf(request.get("deviceId").toString());
        Long employeeId = Long.valueOf(request.get("employeeId").toString());
        String purpose = (String) request.get("purpose");
        String notes = (String) request.get("notes");
        
        return new ResponseEntity<>(assignmentService.issueDevice(deviceId, employeeId, purpose, notes), HttpStatus.CREATED);
    }
    
    @PutMapping("/return/{assignmentId}")
    public ResponseEntity<Assignment> returnDevice(@PathVariable Long assignmentId, @RequestParam String condition) {
        return ResponseEntity.ok(assignmentService.returnDevice(assignmentId, DeviceCondition.valueOf(condition.toUpperCase())));
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<Assignment>> getActiveAssignments() {
        return ResponseEntity.ok(assignmentService.getActiveAssignments());
    }
    
    @GetMapping
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }
}