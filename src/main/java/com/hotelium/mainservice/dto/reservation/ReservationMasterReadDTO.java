package com.hotelium.mainservice.dto.reservation;

import com.hotelium.mainservice.domain.reservation.ReservationMaster;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class ReservationMasterReadDTO extends ReservationMasterWriteDTO {
    private Long id;
    private ReservationMaster.ReservationStatus status;
    private Long accountTransactionId;
    private Date checkInDate;
    private Date checkOutDate;
    private String roomCode;

}
