package com.airtel.inventory.controller;

import com.airtel.inventory.model.*;
import com.airtel.inventory.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {
    
    @Autowired
    private DeviceService deviceService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    // Device Registration Form
    @GetMapping("/devices/register")
    public String showDeviceForm(Model model) {
        model.addAttribute("device", new Device());
        model.addAttribute("conditions", DeviceCondition.values());
        return "device-form";
    }
    
    @PostMapping("/devices/register")
    public String registerDevice(@ModelAttribute Device device) {
        deviceService.registerDevice(device);
        return "redirect:/devices/list";
    }
    
    @GetMapping("/devices/list")
    public String listDevices(Model model) {
        model.addAttribute("devices", deviceService.getAllDevices());
        return "device-list";
    }
    
    // Employee Registration Form
    @GetMapping("/employees/register")
    public String showEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }
    
    @PostMapping("/employees/register")
    public String registerEmployee(@ModelAttribute Employee employee) {
        employeeService.registerEmployee(employee);
        return "redirect:/employees/list";
    }
    
    @GetMapping("/employees/list")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employee-list";
    }
    
    // Assign Device Form
    @GetMapping("/assignments/new")
    public String showAssignForm(Model model) {
        model.addAttribute("devices", deviceService.getAllAvailableDevices());
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "assign-form";
    }
    
    @PostMapping("/assignments/new")
    public String assignDevice(@RequestParam Long deviceId, 
                               @RequestParam Long employeeId,
                               @RequestParam String purpose,
                               @RequestParam(required = false) String notes) {
        assignmentService.issueDevice(deviceId, employeeId, purpose, notes);
        return "redirect:/assignments/active";
    }
    
    // Return Device Form
    @GetMapping("/assignments/return")
    public String showReturnForm(Model model) {
        model.addAttribute("assignments", assignmentService.getActiveAssignments());
        model.addAttribute("conditions", DeviceCondition.values());
        return "return-form";
    }
    
    @PostMapping("/assignments/return")
    public String returnDevice(@RequestParam Long assignmentId,
                               @RequestParam DeviceCondition condition) {
        assignmentService.returnDevice(assignmentId, condition);
        return "redirect:/assignments/active";
    }
    
    @GetMapping("/assignments/active")
    public String activeAssignments(Model model) {
        model.addAttribute("assignments", assignmentService.getActiveAssignments());
        return "assignment-list";
    }
    
    // Search Page
    @GetMapping("/search")
    public String searchPage() {
        return "search";
    }
    
    @GetMapping("/reports")
    public String reportsPage(Model model) {
        model.addAttribute("totalDevices", deviceService.getAllDevices().size());
        model.addAttribute("availableDevices", deviceService.getAllAvailableDevices().size());
        model.addAttribute("totalEmployees", employeeService.getAllEmployees().size());
        model.addAttribute("activeAssignments", assignmentService.getActiveAssignments().size());
        return "reports";
    }
    
    // ========== DELETE OPERATIONS (ADMIN ONLY) ==========
    @GetMapping("/devices/delete/{id}")
    public String deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return "redirect:/devices/list?deleted";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees/list?deleted";
    }

    // ========== UPDATE OPERATIONS (ADMIN ONLY) ==========
    @GetMapping("/devices/edit/{id}")
    public String showEditDeviceForm(@PathVariable Long id, Model model) {
        model.addAttribute("device", deviceService.getDeviceById(id));
        model.addAttribute("conditions", DeviceCondition.values());
        return "device-edit";
    }

    @PostMapping("/devices/edit/{id}")
    public String updateDevice(@PathVariable Long id, @ModelAttribute Device device) {
        device.setId(id);
        deviceService.updateDevice(device);
        return "redirect:/devices/list?updated";
    }

    @GetMapping("/employees/edit/{id}")
    public String showEditEmployeeForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "employee-edit";
    }

    @PostMapping("/employees/edit/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee) {
        employee.setId(id);
        employeeService.updateEmployee(employee);
        return "redirect:/employees/list?updated";
    }
 // ========== USER MANAGEMENT (ADMIN ONLY) ==========

    @GetMapping("/users/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user-list";
    }

    @GetMapping("/users/register")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", new String[]{"ADMIN", "STAFF"});
        return "user-form";
    }

    @PostMapping("/users/register")
    public String registerUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users/list";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", new String[]{"ADMIN", "STAFF"});
        return "user-edit";
    }

    @PostMapping("/users/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
        userService.updateUser(id, user);
        return "redirect:/users/list?updated";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users/list?deleted";
    }
}