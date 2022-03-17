package com.hotelium.mainservice.service.auth;

import com.hotelium.mainservice.domain.auth.AuthUser;
import com.hotelium.mainservice.dto.auth.AuthUserLoginDTO;
import com.hotelium.mainservice.dto.auth.AuthUserRegisterDTO;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface AuthUserService {
    Boolean login(AuthUserLoginDTO authUserLoginDTO);

    AuthUser getSessionInfo();

    AuthUser getSessionInfo(String username);

    AuthUser save(AuthUserRegisterDTO authUserRegisterDTO);

}
