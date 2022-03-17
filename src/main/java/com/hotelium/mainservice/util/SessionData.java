package com.hotelium.mainservice.util;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Melih ŞİNİK
 * @since 17.10.2021
 */
@Builder
@Getter
@ToString
public class SessionData {
    private String userId;
    private String userName;
    private String orgId;
}
