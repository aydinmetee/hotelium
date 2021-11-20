package com.hotelium.mainservice.dto.reservation;

import com.hotelium.mainservice.domain.AccountTransaction;
import com.hotelium.mainservice.domain.reservation.ReservationMaster;
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
public class ReservationMasterReadDTO extends ReservationMasterWriteDTO {
    private Long id;
    private ReservationMaster.ReservationStatus status;
    private Long accountTransactionId;
    private Date checkInDate;
    private Date checkOutDate;
    private String roomCode;
    private BigDecimal bookAmount;
    private AccountTransaction.TransactionSource source;

}
