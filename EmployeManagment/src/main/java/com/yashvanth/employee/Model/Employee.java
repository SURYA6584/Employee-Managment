package com.yashvanth.employee.Model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String profilePic;
    private String name;
    private String address;
    private double ctc;
    private String project;
    private String emailId;
    private String password;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference  // Handle the serialization of Attendance from Employee
    private List<Attendance> attendanceRecords;
    
    @Override
    public String toString() {
        return "Employee{id=" + id + ", name=" + name + ", emailId=" + emailId + ", ctc=" + ctc + "}";
    }
}
