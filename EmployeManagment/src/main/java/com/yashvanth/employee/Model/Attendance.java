package com.yashvanth.employee.Model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime loginTime;
    private LocalTime logoutTime;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonBackReference  // Prevent the serialization of Employee from Attendance
    private Employee employee;
    
    @Override
    public String toString() {
        return "Attendance{id=" + id + ", date=" + date + ", loginTime=" + loginTime + ", logoutTime=" + logoutTime + "}";
    }
}
