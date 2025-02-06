package com.yashvanth.employee.serviceImp;



import org.springframework.stereotype.Service;

import com.yashvanth.employee.Model.Attendance;
import com.yashvanth.employee.repository.AttendanceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    
    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }

    public Attendance addAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }
    // Method to get attendance by ID
    public List<Attendance> getAttendanceByEmployeeId(Long employeeId) {
        // Fetch attendance records by employee ID
        List<Attendance> attendanceList = attendanceRepository.findByEmployeeId(employeeId);
        
        // If records are found, return the list; otherwise, return an empty list or handle as needed
        return attendanceList.isEmpty() ? new ArrayList<>() : attendanceList;
    }

}
