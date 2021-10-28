package com.hotelium.mainservice.service.impl;

import com.hotelium.mainservice.domain.AccountTransaction;
import com.hotelium.mainservice.dto.AccountTransactionBalanceDTO;
import com.hotelium.mainservice.repository.AccountTransactionRepository;
import com.hotelium.mainservice.service.AccountTransactionCalculateService;
import com.hotelium.mainservice.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Mete Aydin
 * @since 24.10.2021
 */
@Service
@RequiredArgsConstructor
public class AccountTransactionCalculateServiceImpl implements AccountTransactionCalculateService {

    private final AccountTransactionRepository accountTransactionRepository;

    @Override
    public AccountTransactionBalanceDTO getMontly() {
        final var balance = new AccountTransactionBalanceDTO();
        balance.setCashIncome(findCashTransactions());
        balance.setBankIncome(findBankTransactions());
        balance.setDebitIncome(findDebitTransactions());
        balance.setTotalIncome(findTotalAmount());
        balance.setTotalExpense(findTotalExpense());
        balance.setCaseBalance(findCashTransactions().subtract(findTotalExpense()));
        balance.setMonthName(new SimpleDateFormat("MMMM").format(findStartDayOfMonth()));
        return balance;
    }

    private Date findStartDayOfMonth() {
        final var startDate = Calendar.getInstance();
        startDate.setTime(DateUtil.startOfMonth(new Date()));
        return startDate.getTime();
    }

    private Date findLastDayOfMonth() {
        final var lastDate = Calendar.getInstance();
        lastDate.setTime(DateUtil.endOfMonth(new Date()));
        return lastDate.getTime();
    }

    private BigDecimal findCashTransactions() {
        final var cashTransactions = accountTransactionRepository.findAccountTransactionsByCreDateBetweenAndTypeAndSource(
                findStartDayOfMonth(), findLastDayOfMonth(), AccountTransaction.TransactionType.INCOME,
                AccountTransaction.TransactionSource.CASH);
        return cashTransactions.stream().map(AccountTransaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal findDebitTransactions() {
        final var debitTransactions = accountTransactionRepository.findAccountTransactionsByCreDateBetweenAndTypeAndSource(
                findStartDayOfMonth(), findLastDayOfMonth(), AccountTransaction.TransactionType.INCOME,
                AccountTransaction.TransactionSource.DEBIT);
        return debitTransactions.stream().map(AccountTransaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal findBankTransactions() {
        final var bankTransactions = accountTransactionRepository.findAccountTransactionsByCreDateBetweenAndTypeAndSource(
                findStartDayOfMonth(), findLastDayOfMonth(), AccountTransaction.TransactionType.INCOME,
                AccountTransaction.TransactionSource.BANK);
        return bankTransactions.stream().map(AccountTransaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal findTotalAmount() {
        return BigDecimal.ZERO.add(findCashTransactions()).add(findDebitTransactions()).add(findBankTransactions());
    }

    private BigDecimal findTotalExpense() {
        final var expenseTransactions = accountTransactionRepository.findAccountTransactionsByCreDateBetweenAndTypeAndSource(
                findStartDayOfMonth(), findLastDayOfMonth(), AccountTransaction.TransactionType.EXPENSE,
                AccountTransaction.TransactionSource.CASH);
        return expenseTransactions.stream().map(AccountTransaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
