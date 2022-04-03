package com.hotelium.mainservice.dto.reservation;

import com.hotelium.mainservice.domain.reservation.ReservationMaster;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class ReservationMasterReadDTO extends ReservationMasterWriteDTO {
    private String id;
    private ReservationMaster.ReservationStatus status;
    private Date checkInDate;
    private Date checkOutDate;
    private String roomCode;
    private String orgId;
    private Date creDate;
    private String creUser;
    private Date updDate;
    private String updUser;
    private Boolean isPayed;

}
