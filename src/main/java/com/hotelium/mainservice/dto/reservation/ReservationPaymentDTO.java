package com.hotelium.mainservice.dto.reservation;

import com.hotelium.mainservice.domain.AccountTransaction;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Mete Aydin
 * @since 22.03.2022
 */
@Getter
@Setter
public class ReservationPaymentDTO {
    private String masterId;
    private BigDecimal amount;
    private AccountTransaction.TransactionSource source;
    private AccountTransaction.Drawee drawee;
    private String draweeId;
}
