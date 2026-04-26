package com.airtel.inventory.controller;

import com.airtel.inventory.model.Device;
import com.airtel.inventory.model.DeviceCondition;
import com.airtel.inventory.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    
    @Autowired
    private DeviceService deviceService;
    
    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        return new ResponseEntity<>(deviceService.registerDevice(device), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }
    
    @GetMapping("/available")
    public ResponseEntity<List<Device>> getAvailableDevices() {
        return ResponseEntity.ok(deviceService.getAllAvailableDevices());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.getDeviceById(id));
    }
    
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Device>> getByType(@PathVariable String type) {
        return ResponseEntity.ok(deviceService.searchByType(type));
    }
    
    @PutMapping("/{id}/condition")
    public ResponseEntity<Device> updateCondition(@PathVariable Long id, @RequestParam String condition) {
        return ResponseEntity.ok(deviceService.updateDeviceCondition(id, DeviceCondition.valueOf(condition.toUpperCase())));
    }
    @GetMapping("/search")
    public ResponseEntity<List<Device>> searchDevices(
            @RequestParam(required = false) String deviceType,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String condition,
            @RequestParam(required = false) Boolean available) {
        
        DeviceCondition deviceCondition = null;
        if (condition != null && !condition.isEmpty()) {
            deviceCondition = DeviceCondition.valueOf(condition.toUpperCase());
        }
        
        List<Device> devices = deviceService.searchDevices(deviceType, brand, deviceCondition, available);
        return ResponseEntity.ok(devices);
    }
 // Add to DeviceController.java

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody Device device) {
        device.setId(id);
        return ResponseEntity.ok(deviceService.updateDevice(device));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}