package com.hotelium.mainservice.dto.account;

import com.hotelium.mainservice.domain.AccountTransaction;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class AccountTransactionWriteDTO {
    private BigDecimal amount;
    private AccountTransaction.TransactionSource source;
    private String description;
    private String reservationMasterId;
    private AccountTransaction.Drawee drawee;
    private String legalId;
    private String nameTitle;

}
