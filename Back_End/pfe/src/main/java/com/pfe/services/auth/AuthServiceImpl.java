package com.pfe.services.auth;

import com.pfe.Repositories.RoleRepository;
import com.pfe.Repositories.UserRepository;
import com.pfe.entites.Role;
import com.pfe.entites.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @PostConstruct
    public void CreateAdminAcccount(){
        Role adminRole = roleRepository.findFirstByLabel("ADMIN") ;
        User adminAccount = userRepository.findFirstByRole(adminRole);

        if(adminAccount == null){
            User newAdminAccount = new User();
            newAdminAccount.setFirstName("Admin");
            newAdminAccount.setEmail("admin@test.com");
            newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
            newAdminAccount.setRole(adminRole);
            userRepository.save(newAdminAccount);
            System.out.println("Admin account created");
        }
    }
}
