package com.marcosgrs.ribeirosevents.domain.repository;

import com.marcosgrs.ribeirosevents.domain.entity.Presence;
import com.marcosgrs.ribeirosevents.domain.entity.Profile;
import com.marcosgrs.ribeirosevents.domain.model.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresenceRepository extends JpaRepository<Presence, String> {

}
