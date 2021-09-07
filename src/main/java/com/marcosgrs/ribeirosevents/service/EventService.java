package com.marcosgrs.ribeirosevents.service;

import com.marcosgrs.ribeirosevents.controllers.dto.EventDto;
import com.marcosgrs.ribeirosevents.controllers.dto.PresenceDto;
import com.marcosgrs.ribeirosevents.domain.entity.Event;
import com.marcosgrs.ribeirosevents.domain.model.EventStatus;
import com.marcosgrs.ribeirosevents.domain.model.UserContext;
import com.marcosgrs.ribeirosevents.domain.repository.EventRepository;
import com.marcosgrs.ribeirosevents.domain.repository.PresenceRepository;
import com.marcosgrs.ribeirosevents.exceptions.BadRequestException;
import com.marcosgrs.ribeirosevents.mapper.EventMapper;
import com.marcosgrs.ribeirosevents.mapper.PresenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserContext userContext;
    private final RibeirosEventsUserDetailsService ribeirosEventsUserDetailsService;
    private final PresenceRepository presenceRepository;
    private static final int MAX_EVENTS = 2;

    @Autowired
    public EventService(EventRepository eventRepository,
                        RibeirosEventsUserDetailsService ribeirosEventsUserDetailsService,
                        UserContext userContext, PresenceRepository presenceRepository) {
        this.eventRepository = eventRepository;
        this.userContext = userContext;
        this.ribeirosEventsUserDetailsService = ribeirosEventsUserDetailsService;
        this.presenceRepository = presenceRepository;
    }

    public String createEvent(EventDto eventDto) {
        var organizer = ribeirosEventsUserDetailsService.findById(userContext.getUserId());
        var eventToSave = EventMapper.toEntity(eventDto, organizer);
        if(!userCanCreateEvent(userContext.getUserId())) {
            throw new BadRequestException(String
                    .format("User cannot have more than %s open events", MAX_EVENTS));
        }
        return eventRepository.save(eventToSave).getId();
    }

    private boolean userCanCreateEvent(String userId) {
        return eventRepository.countAllByOrganizerIdAndStatus(userId,
                EventStatus.OPEN) <= MAX_EVENTS - 1;
    }

    private Event findById(String eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new BadRequestException(String.format("Event '%s' not found", eventId)));
    }

    public void updatePresence(PresenceDto presenceDto, String eventId) {
        var userRepository =
        presenceRepository.save(PresenceMapper
                .toEntity(presenceDto, findById(eventId),
                ribeirosEventsUserDetailsService.findById(userContext.getUserId())));

    }
}
