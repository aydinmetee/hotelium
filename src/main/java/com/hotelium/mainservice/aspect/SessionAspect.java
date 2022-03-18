package com.hotelium.mainservice.aspect;

import com.hotelium.mainservice.service.auth.AuthUserService;
import com.hotelium.mainservice.util.JwtUtil;
import com.hotelium.mainservice.util.SessionContext;
import com.hotelium.mainservice.util.SessionData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * @author Mete Aydın
 * @since 18.03.2022
 */
@Component
@Aspect
@Slf4j
@Order(0)
@RequiredArgsConstructor
public class SessionAspect {

    private final JwtUtil jwtUtil;
    private final AuthUserService authUserService;

    @Before("endPoint()")
    public void before() {
        var authentication = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final var authUser = authUserService.getSessionInfo(authentication.getUsername());
        var sessionData = SessionData.builder()
                .userName(authUser.getUsername())
                .userId(authUser.getId())
                .orgId(authUser.getOrgId())
                .build();
        SessionContext.setSessionData(sessionData);
        log.info("Session data: {}", sessionData.toString());
    }

    @Pointcut("execution(* com.hotelium.mainservice.endpoint..*(..)) "+
            "&& !@annotation(com.hotelium.mainservice.annotation.NoSession)")
    public void endPoint() {
    }

    @After("endPoint()")
    public void after() {
        log.info("Removing session data.");
    }
}
