package tn.conge.domain.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.conge.domain.Entities.ERole;
import tn.conge.domain.Entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
