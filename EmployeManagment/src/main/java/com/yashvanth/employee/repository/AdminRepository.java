package com.yashvanth.employee.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.yashvanth.employee.Model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmailId(String emailId);
}
