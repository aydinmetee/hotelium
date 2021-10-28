package com.hotelium.mainservice.repository.reservation;

import com.hotelium.mainservice.domain.reservation.ReservationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface ReservationMasterRepository extends JpaRepository<ReservationMaster, Long>, JpaSpecificationExecutor<ReservationMaster> {
}
