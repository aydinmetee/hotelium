package com.hotelium.mainservice.serviceview;

import com.hotelium.mainservice.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
public interface AccountTransactionServiceView {
    AccountTransactionReadDTO createExpense(AccountTransactionWriteDTO accountTransactionWriteDTO);

    AccountTransactionReadDTO delete(String id);

    Page<AccountTransactionReadDTO> search(AccountTransactionSearchCriteriaDTO filter, Pageable pageable);

    AccountTransactionBalanceDTO getMontly() throws ParseException;

    AccountTransactionReadDTO updateSource(AccountTransactionSourceUpdateDTO updateDTO);
}
