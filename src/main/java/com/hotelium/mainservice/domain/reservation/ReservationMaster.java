package com.hotelium.mainservice.domain.reservation;

import com.hotelium.mainservice.domain.AccountTransaction;
import com.hotelium.mainservice.domain.BaseEntity;
import com.hotelium.mainservice.domain.Room;
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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

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
@Table(name = "reservation_mst")
public class ReservationMaster extends BaseEntity {
    @JoinColumn(name = "room_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "transaction_id")
    @OneToOne(fetch = FetchType.LAZY)
    private AccountTransaction accountTransaction;
    @Column(name = "check_in")
    private Date checkInDate;
    @Column(name = "check_out")
    private Date checkOutDate;
    @Column(name = "reservation_date")
    private Date reservationDate;
    @Column(name = "duration")
    private Long duration;

    public enum ReservationStatus {
        NEW,
        BOOKING,
        COMPLETED,
        CANCELLED
    }
}
