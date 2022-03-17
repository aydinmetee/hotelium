package com.hotelium.mainservice.domain.auth;

import com.hotelium.mainservice.util.IdGenerator;
import lombok.*;

import javax.persistence.*;

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
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @Column(name = "ORG_ID", nullable = false, length = 36)
    private String orgId;
    @Column(name = "username", length = 100, unique = true)
    private String username;
    @Column(name = "password", length = 200)
    private String password;
    @Column(name = "email", length = 100, unique = true)
    private String email;
    @Column(name = "auth_type")
    @Enumerated(EnumType.STRING)
    private AuthType type;

    public enum AuthType {
        USER,
        ADMIN
    }

    @PrePersist
    public void onPrePersist() {
        setId(IdGenerator.getUUID());
    }

}
