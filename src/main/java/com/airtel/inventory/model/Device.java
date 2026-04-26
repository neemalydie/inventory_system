package com.airtel.inventory.model;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "devices")
public class Device {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String serialNumber;
    
    @Column(nullable = false)
    private String deviceType;
    
    private String brand;
    private String model;
    private String ram;
    private String storage;
    private String processor;
    private String operatingSystem;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "device_condition") 
    private DeviceCondition condition;
    
    private boolean isAvailable = true;
    
    @Column(length = 500)
    private String notes;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    
    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }
    
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    
    public String getRam() { return ram; }
    public void setRam(String ram) { this.ram = ram; }
    
    public String getStorage() { return storage; }
    public void setStorage(String storage) { this.storage = storage; }
    
    public String getProcessor() { return processor; }
    public void setProcessor(String processor) { this.processor = processor; }
    
    public String getOperatingSystem() { return operatingSystem; }
    public void setOperatingSystem(String operatingSystem) { this.operatingSystem = operatingSystem; }
    
    public DeviceCondition getCondition() { return condition; }
    public void setCondition(DeviceCondition condition) { this.condition = condition; }
    
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}