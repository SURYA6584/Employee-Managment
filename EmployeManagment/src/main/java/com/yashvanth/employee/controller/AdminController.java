package com.yashvanth.employee.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashvanth.employee.Model.Admin;
import com.yashvanth.employee.Model.Attendance;
import com.yashvanth.employee.Model.Employee;
import com.yashvanth.employee.serviceImp.AdminService;
import com.yashvanth.employee.serviceImp.AttendanceService;
import com.yashvanth.employee.serviceImp.EmployeeService;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final EmployeeService employeeService;
    private final AttendanceService attendanceService;

    @Autowired
    private AdminService adminService;
    public AdminController(EmployeeService employeeService, AttendanceService attendanceService) {
        this.employeeService = employeeService;
        this.attendanceService = attendanceService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/attendances")
    public List<Attendance> getAllAttendanceRecords() {
        return attendanceService.getAllAttendances();
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin loginRequest) {
    	System.out.println(loginRequest.getEmailId() + ""+loginRequest.getPassword());
        Admin admin = adminService.validateAdminLogin(loginRequest.getEmailId(), loginRequest.getPassword());
        
        
        if (admin != null) {
            // Assuming you generate JWT or some token here, for simplicity returning success
            return ResponseEntity.ok().body("{\"success\": true, \"token\": \"dummy-token\"}");
        } else {
            return ResponseEntity.status(401).body("{\"success\": false, \"message\": \"Invalid credentials\"}");
        }
    }
}
