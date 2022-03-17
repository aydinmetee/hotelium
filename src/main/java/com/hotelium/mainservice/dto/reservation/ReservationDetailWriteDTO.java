package com.hotelium.mainservice.dto.reservation;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class ReservationDetailWriteDTO {
    private String reservationMasterId;
    private String customerId;
}
