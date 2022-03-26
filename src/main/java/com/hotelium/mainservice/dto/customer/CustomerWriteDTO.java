package com.hotelium.mainservice.dto.customer;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class CustomerWriteDTO {
    private String name;
    private String lastname;
    private String legalId;
    private String phone;
    private Boolean valid;
}
