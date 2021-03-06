package com.example.ecommerce.repository;

import com.example.ecommerce.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String Email);

    Boolean existsByEmail(String Email);


}
