package com.pfe.Repositories;

import com.pfe.entites.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
Role findFirstByLabel(String label);
}
