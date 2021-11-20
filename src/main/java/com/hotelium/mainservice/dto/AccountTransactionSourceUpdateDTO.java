package com.hotelium.mainservice.dto;

import com.hotelium.mainservice.domain.AccountTransaction;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AccountTransactionSourceUpdateDTO {
    @NotNull
    private Long id;
    @NotNull
    private AccountTransaction.TransactionSource source;

}
