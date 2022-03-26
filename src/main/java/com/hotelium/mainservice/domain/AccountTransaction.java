package com.hotelium.mainservice.domain;

import com.hotelium.mainservice.domain.reservation.ReservationMaster;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
    @JoinColumn(name = "res_id")
    @OneToOne(fetch = FetchType.LAZY)
    private ReservationMaster reservationMaster;
    @Column(name = "name_title")
    private String nameTitle;
    @Column(name = "legalId")
    private String legalId;
    @Column(name = "drawee")
    @Enumerated(EnumType.STRING)
    private Drawee drawee;

    public enum TransactionType {
        INCOME,
        EXPENSE
    }

    public enum TransactionSource {
        DEBIT,
        CASH,
        BANK
    }

    public enum Drawee {
        PERSON,
        LEGAL
    }
}
