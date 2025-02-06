package com.yashvanth.employee.serviceImp;



import org.springframework.stereotype.Service;

import com.yashvanth.employee.Model.Admin;
import com.yashvanth.employee.repository.AdminRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

 
    
    public Admin validateAdminLogin(String email, String password) {
        // Find admin by email
        Admin admin = adminRepository.findByEmailId(email);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin; // Valid credentials
        }
        return null; // Invalid credentials
    }
}
