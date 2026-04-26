package com.airtel.inventory.repository;

import com.airtel.inventory.model.Device;
import com.airtel.inventory.model.DeviceCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long>, JpaSpecificationExecutor<Device> {
    
    List<Device> findByDeviceType(String deviceType);
    
    List<Device> findByCondition(DeviceCondition condition);
    
    List<Device> findByIsAvailableTrue();
    
    Device findBySerialNumber(String serialNumber);
    
    @Query("SELECT d FROM Device d WHERE d.brand LIKE %:brand%")
    List<Device> findByBrandContaining(@Param("brand") String brand);
}