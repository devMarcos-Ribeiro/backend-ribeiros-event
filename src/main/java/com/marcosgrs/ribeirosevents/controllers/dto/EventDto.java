package com.marcosgrs.ribeirosevents.controllers.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    @NotNull(message = "Event name cannot be null.")
    @Length(max = 255, min = 3, message = "Event name  must be between 3 and 255 characters.")
    private String eventName;

    private LocalDateTime eventDate;

}
