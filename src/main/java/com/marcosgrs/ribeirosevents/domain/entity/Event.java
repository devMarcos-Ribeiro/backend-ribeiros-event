package com.marcosgrs.ribeirosevents.domain.entity;

import com.marcosgrs.ribeirosevents.domain.model.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private LocalDateTime date;
    @OneToOne
    private User organizer;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @OneToOne
    private Address address;
}
