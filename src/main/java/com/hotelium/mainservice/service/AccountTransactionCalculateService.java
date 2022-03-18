package com.hotelium.mainservice.service;

import com.hotelium.mainservice.dto.account.AccountTransactionBalanceDTO;

/**
 * @author Mete Aydin
 * @date 24.10.2021
 */
public interface AccountTransactionCalculateService {
    AccountTransactionBalanceDTO getMontly();

}
