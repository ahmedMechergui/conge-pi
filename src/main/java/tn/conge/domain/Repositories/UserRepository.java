package tn.conge.domain.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.conge.domain.Entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    User findByEmail(String email);
    User findByPasswordResetToken(String passwordResetToken);
}
