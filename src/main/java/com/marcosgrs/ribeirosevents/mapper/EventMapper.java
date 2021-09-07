package com.marcosgrs.ribeirosevents.mapper;

import com.marcosgrs.ribeirosevents.controllers.dto.EventDto;
import com.marcosgrs.ribeirosevents.domain.entity.Event;
import com.marcosgrs.ribeirosevents.domain.entity.User;
import com.marcosgrs.ribeirosevents.domain.model.EventStatus;

public class EventMapper {

    public static Event toEntity(EventDto eventDto, User organizer) {
        return Event.builder()
                .date(eventDto.getEventDate())
                .name(eventDto.getEventName())
                .organizer(organizer)
                .status(EventStatus.OPEN)
                .build();
    }
}
