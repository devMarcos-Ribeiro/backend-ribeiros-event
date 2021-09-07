package com.marcosgrs.ribeirosevents.controllers;

import com.marcosgrs.ribeirosevents.controllers.dto.DefaultResponse;
import com.marcosgrs.ribeirosevents.controllers.dto.EventDto;
import com.marcosgrs.ribeirosevents.controllers.dto.PresenceDto;
import com.marcosgrs.ribeirosevents.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/events")
    public ResponseEntity<String> createEventUsingPost(@RequestBody EventDto eventDto) {
        return ResponseEntity.ok(eventService.createEvent(eventDto));
    }

    @PutMapping("/events/presence/{eventId}")
    public ResponseEntity<DefaultResponse> updatePresence(@RequestBody PresenceDto presenceDto,
       @PathVariable String eventId) {
        eventService.updatePresence(presenceDto, eventId);
       return ResponseEntity.ok(new DefaultResponse("ACCEPTED"));
    }
}
