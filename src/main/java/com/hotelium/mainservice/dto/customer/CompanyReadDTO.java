package com.hotelium.mainservice.dto.customer;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class CompanyReadDTO extends CompanyWriteDTO {
    private String id;
}
