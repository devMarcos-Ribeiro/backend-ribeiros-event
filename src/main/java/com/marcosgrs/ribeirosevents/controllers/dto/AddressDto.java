package com.marcosgrs.ribeirosevents.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    @NotNull(message = "Event street cannot be null.")
    @Length(max = 255, min = 1, message = "Event name  must be between 1 and 255 characters.")
    private String street;

    @NotNull(message = "Event neighborhood cannot be null.")
    @Length(max = 255, min = 1, message = "Event name  must be between 1 and 255 characters.")
    private String neighbourhood;

    @NotNull(message = "Event city cannot be null.")
    @Length(max = 255, min = 1, message = "Event city must be between 1 and 255 characters.")
    private String city;

    @NotNull(message = "Event number cannot be null.")
    private String number;

}
