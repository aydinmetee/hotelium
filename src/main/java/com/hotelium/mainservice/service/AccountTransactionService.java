package com.hotelium.mainservice.service;

import com.hotelium.mainservice.domain.AccountTransaction;
import com.hotelium.mainservice.dto.AccountTransactionSearchCriteriaDTO;
import com.hotelium.mainservice.dto.AccountTransactionWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface AccountTransactionService {
    AccountTransaction create(AccountTransactionWriteDTO accountTransactionWriteDTO);

    AccountTransaction getById(Long id);

    AccountTransaction delete(Long id);

    Page<AccountTransaction> search(AccountTransactionSearchCriteriaDTO filter, Pageable pageable);
}
