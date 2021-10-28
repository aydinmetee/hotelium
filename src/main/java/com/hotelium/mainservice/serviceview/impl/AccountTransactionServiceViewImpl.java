package com.hotelium.mainservice.serviceview.impl;

import com.hotelium.mainservice.domain.AccountTransaction;
import com.hotelium.mainservice.dto.AccountTransactionBalanceDTO;
import com.hotelium.mainservice.dto.AccountTransactionReadDTO;
import com.hotelium.mainservice.dto.AccountTransactionSearchCriteriaDTO;
import com.hotelium.mainservice.dto.AccountTransactionWriteDTO;
import com.hotelium.mainservice.service.AccountTransactionCalculateService;
import com.hotelium.mainservice.service.AccountTransactionService;
import com.hotelium.mainservice.serviceview.AccountTransactionServiceView;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class AccountTransactionServiceViewImpl implements AccountTransactionServiceView {
    private final AccountTransactionService accountTransactionService;
    private final AccountTransactionCalculateService accountTransactionCalculateService;
    private final ModelMapper modelMapper;

    @Override
    public AccountTransactionReadDTO createExpense(AccountTransactionWriteDTO accountTransactionWriteDTO) {
        accountTransactionWriteDTO.setType(AccountTransaction.TransactionType.EXPENSE);
        return convertToDto(accountTransactionService.create(accountTransactionWriteDTO));
    }

    @Override
    public AccountTransactionReadDTO delete(Long id) {
        return convertToDto(accountTransactionService.delete(id));
    }

    @Override
    public Page<AccountTransactionReadDTO> search(AccountTransactionSearchCriteriaDTO filter, Pageable pageable) {
        return accountTransactionService.search(filter, pageable).map(this::convertToDto);
    }

    @Override
    public AccountTransactionBalanceDTO getMontly() throws ParseException {
        return accountTransactionCalculateService.getMontly();
    }

    private AccountTransactionReadDTO convertToDto(AccountTransaction accountTransaction) {
        return modelMapper.map(accountTransaction, AccountTransactionReadDTO.class);
    }
}
