package com.example.springtest.domain.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//User의 CRUD담당
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
