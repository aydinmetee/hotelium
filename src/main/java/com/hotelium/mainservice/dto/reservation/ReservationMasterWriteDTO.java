package com.hotelium.mainservice.dto.reservation;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
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
    private Long duration;
    private BigDecimal dailyAmount;
}
