package com.hotelium.mainservice.service.impl;

import com.hotelium.mainservice.domain.AccountTransaction;
import com.hotelium.mainservice.dto.account.AccountTransactionSearchCriteriaDTO;
import com.hotelium.mainservice.dto.account.AccountTransactionSourceUpdateDTO;
import com.hotelium.mainservice.dto.account.AccountTransactionWriteDTO;
import com.hotelium.mainservice.exception.ServiceExecutionException;
import com.hotelium.mainservice.repository.AccountTransactionRepository;
import com.hotelium.mainservice.service.AccountTransactionService;
import com.hotelium.mainservice.service.reservation.ReservationMasterService;
import com.hotelium.mainservice.util.MessageUtil;
import com.hotelium.mainservice.util.SessionContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {
    private final AccountTransactionRepository accountTransactionRepository;
    private final ModelMapper modelMapper;
    private final MessageUtil messageUtil;
    private final ReservationMasterService reservationMasterService;

    @Autowired
    public AccountTransactionServiceImpl(AccountTransactionRepository accountTransactionRepository,
                                         ModelMapper modelMapper,
                                         MessageUtil messageUtil,
                                         @Lazy ReservationMasterService reservationMasterService) {
        this.accountTransactionRepository = accountTransactionRepository;
        this.modelMapper = modelMapper;
        this.messageUtil = messageUtil;
        this.reservationMasterService = reservationMasterService;
    }


    @Override
    public AccountTransaction createIncome(AccountTransactionWriteDTO accountTransactionWriteDTO) {
        final var reservationMaster = reservationMasterService.getById(accountTransactionWriteDTO.getReservationMasterId());
        final var accountDB = modelMapper.map(accountTransactionWriteDTO, AccountTransaction.class);
        accountDB.setReservationMaster(reservationMaster);
        accountDB.setType(AccountTransaction.TransactionType.INCOME);
        return accountTransactionRepository.save(accountDB);
    }

    @Override
    public AccountTransaction createExpense(AccountTransactionWriteDTO accountTransactionWriteDTO) {
        final var accountDB = modelMapper.map(accountTransactionWriteDTO, AccountTransaction.class);
        accountDB.setReservationMaster(null);
        accountDB.setType(AccountTransaction.TransactionType.EXPENSE);
        return accountTransactionRepository.save(accountDB);
    }

    @Override
    public AccountTransaction getById(String id) {
        final var accountTransaction = accountTransactionRepository.findByIdAndOrgId(id,
                SessionContext.getSessionData().getOrgId());
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
    @Transactional
    public void deleteForReservation(String resarvationMasterId) {
        final var filter = new AccountTransactionSearchCriteriaDTO();
        filter.setReservationMasterId(resarvationMasterId);
        final var deletedAccount = search(filter, PageRequest.of(0, 1)).getContent().get(0);
        accountTransactionRepository.deleteById(deletedAccount.getId());
    }

    @Override
    public Page<AccountTransaction> search(AccountTransactionSearchCriteriaDTO filter, Pageable pageable) {
        filter.setOrgId(SessionContext.getSessionData().getOrgId());
        return accountTransactionRepository.findAll(filter.accountTransactionSearchCriteriaFieldMapper(filter), pageable);
    }

    @Override
    public AccountTransaction updateTransactionSource(AccountTransactionSourceUpdateDTO updateDTO) {
        final var acc = getById(updateDTO.getId());
        acc.setSource(updateDTO.getSource());
        return accountTransactionRepository.save(acc);
    }

    @Override
    public Boolean checkPayment(String reservationMasterId) {
        final var filter = new AccountTransactionSearchCriteriaDTO();
        filter.setReservationMasterId(reservationMasterId);
        final var result = search(filter, PageRequest.of(0, 1));
        if (result.hasContent()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


}
