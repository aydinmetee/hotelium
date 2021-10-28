package com.hotelium.mainservice.repository;

import com.hotelium.mainservice.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {
}
