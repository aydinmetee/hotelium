package com.hotelium.mainservice.repository;

import com.hotelium.mainservice.domain.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, String>, JpaSpecificationExecutor<AccountTransaction> {
    List<AccountTransaction> findAccountTransactionsByCreDateBetweenAndTypeAndSource(
            Date toDate, Date fromDate, AccountTransaction.TransactionType type, AccountTransaction.TransactionSource source);

    Optional<AccountTransaction> findByIdAndOrgId(String id, String orgId);
}
