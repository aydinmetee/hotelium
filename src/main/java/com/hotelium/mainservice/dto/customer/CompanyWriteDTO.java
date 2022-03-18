package com.hotelium.mainservice.dto.customer;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class CompanyWriteDTO {
    private String nameTitle;
    private String address;
    private String legalNo;
    private String taxOffice;
}
