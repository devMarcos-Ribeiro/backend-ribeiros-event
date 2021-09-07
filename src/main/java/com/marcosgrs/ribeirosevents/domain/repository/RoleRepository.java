package com.marcosgrs.ribeirosevents.domain.repository;

import com.marcosgrs.ribeirosevents.domain.entity.Profile;
import com.marcosgrs.ribeirosevents.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Profile, String> {

    Optional<Profile> findByAuthority(String authority);

}
