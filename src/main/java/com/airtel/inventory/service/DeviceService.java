package com.airtel.inventory.service;

import com.airtel.inventory.model.Device;
import java.util.ArrayList;
import com.airtel.inventory.model.DeviceCondition;
import com.airtel.inventory.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeviceService {
    
    @Autowired
    private DeviceRepository deviceRepository;
    
    public Device registerDevice(Device device) {
        device.setCreatedAt(LocalDateTime.now());
        device.setUpdatedAt(LocalDateTime.now());
        if (device.getCondition() == null) {
            device.setCondition(DeviceCondition.NEW);
        }
        return deviceRepository.save(device);
    }
    
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
    
    public List<Device> getAllAvailableDevices() {
        return deviceRepository.findByIsAvailableTrue();
    }
    
    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Device not found"));
    }
    
    public Device updateDeviceCondition(Long deviceId, DeviceCondition newCondition) {
        Device device = getDeviceById(deviceId);
        device.setCondition(newCondition);
        device.setUpdatedAt(LocalDateTime.now());
        return deviceRepository.save(device);
    }
    
    public List<Device> searchByType(String deviceType) {
        return deviceRepository.findByDeviceType(deviceType.toUpperCase());
    }
    public List<Device> searchDevices(String deviceType, String brand, DeviceCondition condition, Boolean available) {
        return deviceRepository.findAll((root, query, cb) -> {
            List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
            if (deviceType != null && !deviceType.isEmpty()) {
                predicates.add(cb.equal(root.get("deviceType"), deviceType));
            }
            if (brand != null && !brand.isEmpty()) {
                predicates.add(cb.equal(root.get("brand"), brand));
            }
            if (condition != null) {
                predicates.add(cb.equal(root.get("condition"), condition));
            }
            if (available != null) {
                predicates.add(cb.equal(root.get("isAvailable"), available));
            }
            return cb.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));
        });
    }
 // Add to DeviceService.java

    public Device updateDevice(Device device) {
        Device existingDevice = getDeviceById(device.getId());
        existingDevice.setSerialNumber(device.getSerialNumber());
        existingDevice.setDeviceType(device.getDeviceType());
        existingDevice.setBrand(device.getBrand());
        existingDevice.setModel(device.getModel());
        existingDevice.setRam(device.getRam());
        existingDevice.setStorage(device.getStorage());
        existingDevice.setProcessor(device.getProcessor());
        existingDevice.setOperatingSystem(device.getOperatingSystem());
        existingDevice.setCondition(device.getCondition());
        existingDevice.setNotes(device.getNotes());
        existingDevice.setUpdatedAt(LocalDateTime.now());
        return deviceRepository.save(existingDevice);
    }

    public void deleteDevice(Long id) {
        Device device = getDeviceById(id);
        if (!device.isAvailable()) {
            throw new RuntimeException("Cannot delete device that is currently assigned to an employee");
        }
        deviceRepository.deleteById(id);
    }
}