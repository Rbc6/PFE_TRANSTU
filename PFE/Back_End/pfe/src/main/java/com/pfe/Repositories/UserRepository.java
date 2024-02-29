package com.pfe.Repositories;

import com.pfe.entites.Role;
import com.pfe.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByRole(Role role);
    Optional<User> findFirstByEmail(String email);

}
