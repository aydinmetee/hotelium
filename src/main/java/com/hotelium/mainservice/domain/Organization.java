package com.hotelium.mainservice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "organization")
public class Organization {
    @Id
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @Column(name = "code", unique = true)
    private String code;
    @Column(name = "name", unique = true)
    private String name;
}
