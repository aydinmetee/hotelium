package com.hotelium.mainservice.serviceview.auth;

import com.hotelium.mainservice.dto.auth.AuthUserLoginDTO;
import com.hotelium.mainservice.dto.auth.AuthUserRegisterDTO;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface AuthUserServiceView {
    Boolean login(AuthUserLoginDTO authUserLoginDTO);

    Boolean save(AuthUserRegisterDTO authUserRegisterDTO);

    UserDetails loadUserByUsername(String username);
}
