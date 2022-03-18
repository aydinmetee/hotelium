package com.hotelium.mainservice.dto.reservation;

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
public class ReservationDetailReadDTO extends ReservationDetailWriteDTO {
    private String id;
    private String customerFullName;
    private BigDecimal customerLegalId;
    private BigDecimal customerPhone;
    private String orgId;
    private Date creDate;
    private String creUser;
    private Date updDate;
    private String updUser;
}
