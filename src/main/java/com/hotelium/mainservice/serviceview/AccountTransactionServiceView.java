package com.hotelium.mainservice.serviceview;

import com.hotelium.mainservice.dto.AccountTransactionBalanceDTO;
import com.hotelium.mainservice.dto.AccountTransactionReadDTO;
import com.hotelium.mainservice.dto.AccountTransactionSearchCriteriaDTO;
import com.hotelium.mainservice.dto.AccountTransactionWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface AccountTransactionServiceView {
    AccountTransactionReadDTO createExpense(AccountTransactionWriteDTO accountTransactionWriteDTO);

    AccountTransactionReadDTO delete(Long id);

    Page<AccountTransactionReadDTO> search(AccountTransactionSearchCriteriaDTO filter, Pageable pageable);

    AccountTransactionBalanceDTO getMontly() throws ParseException;
}
