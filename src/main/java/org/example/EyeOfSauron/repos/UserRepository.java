package org.example.EyeOfSauron.repos;

import org.example.EyeOfSauron.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
