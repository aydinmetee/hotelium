package com.hotelium.mainservice.dto;

import com.hotelium.mainservice.domain.Room;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class RoomReadDTO extends RoomWriteDTO {
    private String id;
    private Room.RoomStatus status;
}
