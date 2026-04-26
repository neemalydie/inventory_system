package com.airtel.inventory.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assignments")
public class Assignment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;
    
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    private LocalDateTime issueDate;
    private LocalDateTime returnDate;
    
    @Enumerated(EnumType.STRING)
    private DeviceCondition conditionAtIssue;
    
    @Enumerated(EnumType.STRING)
    private DeviceCondition conditionAtReturn;
    
    private String purpose;
    private String notes;
    
    private boolean isReturned = false;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Device getDevice() { return device; }
    public void setDevice(Device device) { this.device = device; }
    
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    
    public LocalDateTime getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDateTime issueDate) { this.issueDate = issueDate; }
    
    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }
    
    public DeviceCondition getConditionAtIssue() { return conditionAtIssue; }
    public void setConditionAtIssue(DeviceCondition conditionAtIssue) { this.conditionAtIssue = conditionAtIssue; }
    
    public DeviceCondition getConditionAtReturn() { return conditionAtReturn; }
    public void setConditionAtReturn(DeviceCondition conditionAtReturn) { this.conditionAtReturn = conditionAtReturn; }
    
    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public boolean isReturned() { return isReturned; }
    public void setReturned(boolean returned) { isReturned = returned; }
}