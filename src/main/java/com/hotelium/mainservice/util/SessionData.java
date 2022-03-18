package com.hotelium.mainservice.util;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Mete Aydın
 * @since 18.03.2022
 */
@Builder
@Getter
@ToString
public class SessionData {
    private String userId;
    private String userName;
    private String orgId;
}
