package com.marcosgrs.ribeirosevents.mapper;

import com.marcosgrs.ribeirosevents.controllers.dto.EventDto;
import com.marcosgrs.ribeirosevents.domain.entity.Address;
import com.marcosgrs.ribeirosevents.domain.entity.Event;
import com.marcosgrs.ribeirosevents.domain.entity.User;
import com.marcosgrs.ribeirosevents.domain.model.EventStatus;
import org.springframework.beans.BeanUtils;

public class EventMapper {

    public static Event toEntity(EventDto eventDto, User organizer) {
        Address address = new Address();
        BeanUtils.copyProperties(eventDto.getAddress(), address);
        return Event.builder()
                .date(eventDto.getEventDate())
                .name(eventDto.getEventName())
                .organizer(organizer)
                .address(address)
                .status(EventStatus.OPEN)
                .build();
    }
}
