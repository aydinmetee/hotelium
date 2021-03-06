package com.hotelium.mainservice.repository.reservation;

import com.hotelium.mainservice.domain.reservation.ReservationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface ReservationMasterRepository extends JpaRepository<ReservationMaster, String>, JpaSpecificationExecutor<ReservationMaster> {
    Optional<ReservationMaster> findByIdAndOrgId(String id, String orgId);

    List<ReservationMaster> findReservationMastersByReservationDateBetweenAndOrgId(Date toDate, Date fromDate,
                                                                                   String orgId);
}
