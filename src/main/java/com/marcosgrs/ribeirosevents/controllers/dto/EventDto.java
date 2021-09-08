package com.marcosgrs.ribeirosevents.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    @NotNull(message = "Event name cannot be null.")
    @Length(max = 255, min = 3, message = "Event name  must be between 3 and 255 characters.")
    private String eventName;

    @NotNull
    @Valid
    private AddressDto address;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy:HH:mm:ss")
    private LocalDateTime eventDate;

}
