package com.hotelium.mainservice.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class AccountTransactionReadDTO extends AccountTransactionWriteDTO {
    private String id;
}
