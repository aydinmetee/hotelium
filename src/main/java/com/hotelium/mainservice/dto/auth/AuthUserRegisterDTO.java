package com.hotelium.mainservice.dto.auth;

import com.hotelium.mainservice.domain.auth.AuthUser;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserRegisterDTO extends AuthUserLoginDTO {
    private AuthUser.AuthType type;
    private String orgId;
}
