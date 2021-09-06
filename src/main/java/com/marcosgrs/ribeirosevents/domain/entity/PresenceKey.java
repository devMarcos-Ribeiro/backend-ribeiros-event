package com.marcosgrs.ribeirosevents.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresenceKey implements Serializable {

    @Column(name = "user_id")
    String userId;

    @Column(name = "event_id")
    String eventId;
}
