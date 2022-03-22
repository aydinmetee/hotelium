package com.hotelium.mainservice.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "room")
public class Room extends BaseEntity {
    @Column(name = "code", unique = true, nullable = false)
    private String code;
    @Column(name = "capacity")
    private Long capacity;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    public enum RoomStatus {
        CLEAN,
        RESERVED,
        DIRTY,
        CLOSED,
        FILLED
    }
}
