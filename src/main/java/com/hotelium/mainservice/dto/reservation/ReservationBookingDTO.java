package com.hotelium.mainservice.dto.reservation;

import com.hotelium.mainservice.domain.AccountTransaction;
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
public class ReservationBookingDTO {
    private String masterId;
    private BigDecimal amount;
    private AccountTransaction.TransactionSource source;
    private Date checkInDate;
}
