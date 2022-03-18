package com.hotelium.mainservice.repository.reservation;

import com.hotelium.mainservice.domain.reservation.ReservationDetail;
import com.hotelium.mainservice.domain.reservation.ReservationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, String>, JpaSpecificationExecutor<ReservationDetail> {
    Optional<ReservationDetail> findByIdAndOrgId(String id, String orgId);

}
