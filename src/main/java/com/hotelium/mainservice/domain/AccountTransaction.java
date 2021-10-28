package com.hotelium.mainservice.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "account_transaction")
public class AccountTransaction extends BaseEntity {
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Column(name = "source")
    @Enumerated(EnumType.STRING)
    private TransactionSource source;
    @Column(name = "description")
    private String description;

    public enum TransactionType {
        INCOME,
        EXPENSE
    }

    public enum TransactionSource {
        DEBIT,
        CASH,
        BANK
    }
}
