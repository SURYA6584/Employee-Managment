package com.yashvanth.employee.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yashvanth.employee.Model.Employee;
import com.yashvanth.employee.serviceImp.EmployeeService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Employee loginRequest) {
    	System.out.println(loginRequest.getEmailId()+
            loginRequest.getPassword());
        Employee employee = employeeService.getEmployeeByEmailAndPassword(
            loginRequest.getEmailId(), 
            loginRequest.getPassword()
        );

        if (employee != null) {
            return ResponseEntity.ok(employee); // Login successful
        } else {
            return ResponseEntity.status(401).body("Invalid email or password"); // Unauthorized
        }
    }

//    @GetMapping("/{id}")
//    public Employee getEmployeeById(@PathVariable Long id) {
//        return employeeService.getEmployeeById(id);
//    }
//
//    @PostMapping
//    public Employee addEmployee(@RequestBody Employee employee) {
//        return employeeService.addEmployee(employee);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteEmployee(@PathVariable Long id) {
//        employeeService.deleteEmployee(id);
//    }
}
