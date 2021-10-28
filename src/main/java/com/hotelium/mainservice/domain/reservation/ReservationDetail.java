package com.hotelium.mainservice.domain.reservation;

import com.hotelium.mainservice.domain.BaseEntity;
import com.hotelium.mainservice.domain.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "reservation_det")
public class ReservationDetail extends BaseEntity {
    @JoinColumn(name = "mst_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ReservationMaster reservationMaster;
    @JoinColumn(name = "customer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
}
