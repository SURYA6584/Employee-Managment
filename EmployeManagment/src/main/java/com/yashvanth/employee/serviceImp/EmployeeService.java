package com.yashvanth.employee.serviceImp;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yashvanth.employee.Model.Attendance;
import com.yashvanth.employee.Model.Employee;
import com.yashvanth.employee.repository.AttendanceRepository;
import com.yashvanth.employee.repository.EmployeeRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    
	  @Autowired
	    private EmployeeRepository employeeRepository;

	    @Autowired
	    private AttendanceRepository attendanceRepository;
    
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    
    public Employee getEmployeeByEmailAndPassword(String email, String password) {
        Employee employee = employeeRepository.findByEmailIdAndPassword(email, password);
        
        if (employee != null) {
            updateLoginTime(employee);  // ✅ Update login time
        }

        return employee;
    }

    private void updateLoginTime(Employee employee) {
        LocalDate today = LocalDate.now();
        Optional<Attendance> existingAttendance = attendanceRepository.findByEmployeeAndDate(employee, today);

        Attendance attendance = existingAttendance.orElse(new Attendance());
        attendance.setEmployee(employee);
        attendance.setDate(today);
        attendance.setLoginTime(LocalTime.now());  // ✅ Set current time

        attendanceRepository.save(attendance);  // ✅ Save record in DB
    }
}
