package com.yashvanth.employee.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yashvanth.employee.Model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmailIdAndPassword(String emailId, String password);
    Optional<Employee> findById(Long emploID);

}
