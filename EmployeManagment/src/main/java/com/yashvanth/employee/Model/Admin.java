package com.yashvanth.employee.Model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Admin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String emailId;

    private String password;
    
    // Role can be "ADMIN" for authorization
    private String role;

    // Getters and Setters
}
