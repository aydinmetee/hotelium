package com.hotelium.mainservice.repository.auth;

import com.hotelium.mainservice.domain.auth.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    AuthUser findAuthUserByUsername(String username);
}
