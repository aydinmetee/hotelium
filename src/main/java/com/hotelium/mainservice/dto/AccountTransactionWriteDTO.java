package com.hotelium.mainservice.dto;

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
    private AccountTransaction.TransactionType type;
    private AccountTransaction.TransactionSource source;
    private String description;

}
