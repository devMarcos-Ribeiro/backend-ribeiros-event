package com.marcosgrs.ribeirosevents.domain.repository;

import com.marcosgrs.ribeirosevents.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

}
