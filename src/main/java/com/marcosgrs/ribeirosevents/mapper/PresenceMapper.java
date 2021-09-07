package com.marcosgrs.ribeirosevents.mapper;

import com.marcosgrs.ribeirosevents.controllers.dto.PresenceDto;
import com.marcosgrs.ribeirosevents.domain.entity.Event;
import com.marcosgrs.ribeirosevents.domain.entity.Presence;
import com.marcosgrs.ribeirosevents.domain.entity.PresenceKey;
import com.marcosgrs.ribeirosevents.domain.entity.User;
import com.marcosgrs.ribeirosevents.domain.model.PresenceType;

public class PresenceMapper {

    public static Presence toEntity(PresenceDto presenceDto, Event event, User user) {
        PresenceKey presenceKey = new PresenceKey();
        presenceKey.setEventId(event.getId());
        presenceKey.setUserId(user.getId());
        return Presence.builder()
                .presenceType(PresenceType.fromValue(presenceDto.getPresenceType()))
                .event(event)
                .user(user)
                .build();
    }
}
