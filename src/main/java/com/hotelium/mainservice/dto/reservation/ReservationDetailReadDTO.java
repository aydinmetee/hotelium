package com.hotelium.mainservice.dto.reservation;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class ReservationDetailReadDTO extends ReservationDetailWriteDTO {
    private Long id;
    private String customerFullName;
    private BigDecimal bookAmount;
}
