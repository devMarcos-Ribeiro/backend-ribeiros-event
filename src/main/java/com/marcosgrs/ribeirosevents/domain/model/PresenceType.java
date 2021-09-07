package com.marcosgrs.ribeirosevents.domain.model;

import com.marcosgrs.ribeirosevents.exceptions.BadRequestException;

import java.util.Arrays;

public enum PresenceType {

    CONFIRMED("CONFIRMED"),
    MAYBE("MAYBE"),
    NO("NO");

    private final String name;

    PresenceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PresenceType fromValue(String eventStatus) {
        for (PresenceType status : PresenceType.values()) {
            if(status.getName().equalsIgnoreCase(eventStatus)) {
                return PresenceType.valueOf(eventStatus.toUpperCase());
            }
        }
        throw new BadRequestException(
                String.format("%s is not a valid format. Available values are: %s",
                        eventStatus, Arrays.toString(PresenceType.values())));
    }
}
