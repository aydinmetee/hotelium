package com.hotelium.mainservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class RoomWriteDTO {
    @NotBlank
    private String code;
    private Long capacity;
}
