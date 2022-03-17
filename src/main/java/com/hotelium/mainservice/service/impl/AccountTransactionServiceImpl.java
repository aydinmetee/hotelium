package com.hotelium.mainservice.service.impl;

import com.hotelium.mainservice.domain.AccountTransaction;
import com.hotelium.mainservice.dto.AccountTransactionSearchCriteriaDTO;
import com.hotelium.mainservice.dto.AccountTransactionSourceUpdateDTO;
import com.hotelium.mainservice.dto.AccountTransactionWriteDTO;
import com.hotelium.mainservice.exception.ServiceExecutionException;
import com.hotelium.mainservice.repository.AccountTransactionRepository;
import com.hotelium.mainservice.service.AccountTransactionService;
import com.hotelium.mainservice.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class AccountTransactionServiceImpl implements AccountTransactionService {
    private final AccountTransactionRepository accountTransactionRepository;
    private final ModelMapper modelMapper;
    private final MessageUtil messageUtil;

    @Override
    public AccountTransaction create(AccountTransactionWriteDTO accountTransactionWriteDTO) {
        final var accountDB = modelMapper.map(accountTransactionWriteDTO, AccountTransaction.class);
        return accountTransactionRepository.save(accountDB);
    }

    @Override
    public AccountTransaction getById(String id) {
        final var accountTransaction = accountTransactionRepository.findById(id);
        if (accountTransaction.isEmpty()) {
            throw new ServiceExecutionException(messageUtil.get("accountTransaction.recordNotFound.exception"));
        }
        return accountTransaction.get();
    }

    @Override
    public AccountTransaction delete(String id) {
        final var deletedAccount = getById(id);
        accountTransactionRepository.deleteById(id);
        return deletedAccount;
    }

    @Override
    public Page<AccountTransaction> search(AccountTransactionSearchCriteriaDTO filter, Pageable pageable) {
        return accountTransactionRepository.findAll(filter.accountTransactionSearchCriteriaFieldMapper(filter), pageable);
    }

    @Override
    public AccountTransaction updateTransactionSource(AccountTransactionSourceUpdateDTO updateDTO) {
        final var acc = getById(updateDTO.getId());
        acc.setSource(updateDTO.getSource());
        return accountTransactionRepository.save(acc);
    }


}
