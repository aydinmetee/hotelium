package com.hotelium.mainservice.dto.reservation;

import lombok.Getter;
import lombok.Setter;

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
    private String customerLegalId;
    private String customerPhone;
    private String orgId;
    private Date creDate;
    private String creUser;
    private Date updDate;
    private String updUser;
    private String companyName;
    private String companyId;
    private String companyLegalNo;
}
