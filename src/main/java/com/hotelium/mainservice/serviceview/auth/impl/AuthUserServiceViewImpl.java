package com.hotelium.mainservice.serviceview.auth.impl;

import com.hotelium.mainservice.dto.auth.AuthUserLoginDTO;
import com.hotelium.mainservice.dto.auth.AuthUserRegisterDTO;
import com.hotelium.mainservice.service.auth.impl.AuthUserServiceImpl;
import com.hotelium.mainservice.serviceview.auth.AuthUserServiceView;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class AuthUserServiceViewImpl implements AuthUserServiceView {
    private final AuthUserServiceImpl authUserService;

    @Override
    public Boolean login(AuthUserLoginDTO authUserLoginDTO) {
        return authUserService.login(authUserLoginDTO);
    }

    @Override
    public Boolean save(AuthUserRegisterDTO authUserRegisterDTO) {
        final var authUser = authUserService.save(authUserRegisterDTO);
        if (Objects.isNull(authUser)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return authUserService.loadUserByUsername(username);
    }
}
