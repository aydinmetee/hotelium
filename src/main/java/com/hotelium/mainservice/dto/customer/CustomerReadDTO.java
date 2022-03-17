package com.hotelium.mainservice.dto.customer;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class CustomerReadDTO extends CustomerWriteDTO {
    private String companyName;
    private String id;
}
