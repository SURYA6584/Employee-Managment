package com.yashvanth.employee.repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yashvanth.employee.Model.Attendance;
import com.yashvanth.employee.Model.Employee;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByEmployeeId(Long employeeId);
    Optional<Attendance> findByEmployeeAndDate(Employee employee, LocalDate date);

}

