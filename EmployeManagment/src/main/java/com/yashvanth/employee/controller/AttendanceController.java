package com.yashvanth.employee.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yashvanth.employee.Model.Attendance;
import com.yashvanth.employee.Model.Employee;
import com.yashvanth.employee.repository.AttendanceRepository;
import com.yashvanth.employee.repository.EmployeeRepository;
import com.yashvanth.employee.serviceImp.AttendanceService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
	
  @Autowired	
  private  EmployeeRepository employeeRepository;
  
  @Autowired
  private AttendanceRepository attendanceRepository;
  
   @Autowired
    private  AttendanceService attendanceService;

   
    @GetMapping("/{id}")
    public ResponseEntity<List<Attendance>> getAttendanceById(@PathVariable Long id) {
        List<Attendance> attendance = attendanceService.getAttendanceByEmployeeId(id);  // Fetching attendance list by employee ID
        
        if (attendance != null && !attendance.isEmpty()) {
            return ResponseEntity.ok(attendance);  // Return attendance list if found
        } else {
            return ResponseEntity.notFound().build();  // Return 404 if no attendance records found
        }
    }

    @PutMapping("/logout/{employeeId}")
    public ResponseEntity<String> updateLogoutTime(@PathVariable Long employeeId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);

           System.out.println("yxyxyxxy"+employeeOpt);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            LocalDate today = LocalDate.now();

            Optional<Attendance> attendanceOpt = attendanceRepository.findByEmployeeAndDate(employee, today);
            if (attendanceOpt.isPresent()) {
                Attendance attendance = attendanceOpt.get();
                attendance.setLogoutTime(LocalTime.now());  // ✅ Update logout time
                attendanceRepository.save(attendance);
                return ResponseEntity.ok("Logout time updated successfully");
            } else {
                return ResponseEntity.badRequest().body("Attendance record not found for today");
            }
        } else {
            return ResponseEntity.badRequest().body("Employee not found");
        }
    }
    
    @GetMapping
    public List<Attendance> getAllAttendanceRecords() {
        return attendanceService.getAllAttendances();
    }

    @PostMapping
    public Attendance addAttendance(@RequestBody Attendance attendance) {
        return attendanceService.addAttendance(attendance);
    }
    
}
