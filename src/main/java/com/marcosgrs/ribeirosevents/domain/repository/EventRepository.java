package com.marcosgrs.ribeirosevents.domain.repository;

import com.marcosgrs.ribeirosevents.domain.entity.Event;
import com.marcosgrs.ribeirosevents.domain.entity.Profile;
import com.marcosgrs.ribeirosevents.domain.model.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {

    Long countAllByOrganizerIdAndStatus(String userId, EventStatus eventStatus);

}
