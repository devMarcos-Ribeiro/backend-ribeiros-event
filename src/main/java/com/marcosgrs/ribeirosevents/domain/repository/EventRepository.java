package com.marcosgrs.ribeirosevents.domain.repository;

import com.marcosgrs.ribeirosevents.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Profile, String> {

    Profile findByAuthority(String authority);

}
