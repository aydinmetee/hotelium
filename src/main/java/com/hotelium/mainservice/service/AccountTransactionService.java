package com.hotelium.mainservice.service;

import com.hotelium.mainservice.domain.AccountTransaction;
import com.hotelium.mainservice.dto.account.AccountTransactionSearchCriteriaDTO;
import com.hotelium.mainservice.dto.account.AccountTransactionSourceUpdateDTO;
import com.hotelium.mainservice.dto.account.AccountTransactionWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
public interface AccountTransactionService {
    AccountTransaction createIncome(AccountTransactionWriteDTO accountTransactionWriteDTO);

    AccountTransaction createExpense(AccountTransactionWriteDTO accountTransactionWriteDTO);

    AccountTransaction getById(String id);

    AccountTransaction delete(String id);

    Page<AccountTransaction> search(AccountTransactionSearchCriteriaDTO filter, Pageable pageable);

    AccountTransaction updateTransactionSource(AccountTransactionSourceUpdateDTO updateDTO);

    Boolean checkPayment(String reservationMasterId);

    void deleteForReservation(String resarvationMasterId);
}
