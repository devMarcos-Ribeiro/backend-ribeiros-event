package com.marcosgrs.ribeirosevents.domain.entity;

import com.marcosgrs.ribeirosevents.domain.model.PresenceType;

import javax.persistence.*;

@Entity
public class Presence {

    @EmbeddedId
    PresenceKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    private Event event;

    @Enumerated(EnumType.STRING)
    PresenceType presenceType;
}
