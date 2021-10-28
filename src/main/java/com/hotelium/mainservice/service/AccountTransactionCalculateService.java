package com.hotelium.mainservice.service;

import com.hotelium.mainservice.dto.AccountTransactionBalanceDTO;

/**
 * @author Mete Aydin
 * @date 24.10.2021
 */
public interface AccountTransactionCalculateService {
    AccountTransactionBalanceDTO getMontly();

}
