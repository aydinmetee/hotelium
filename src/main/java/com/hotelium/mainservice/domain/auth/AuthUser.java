package com.hotelium.mainservice.domain.auth;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "auth_user")
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", length = 100, unique = true)
    private String username;
    @Column(name = "password", length = 200)
    private String password;
    @Column(name = "auth_type")
    @Enumerated(EnumType.STRING)
    private AuthType type;

    public enum AuthType {
        USER,
        ADMIN
    }

}
