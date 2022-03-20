package com.hotelium.mainservice.dto.reservation;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class ReservationMasterWriteDTO {
    private String roomId;
    private String description;
    private Date reservationDate;

}
