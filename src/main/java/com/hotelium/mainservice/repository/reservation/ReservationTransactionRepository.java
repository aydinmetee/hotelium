package com.hotelium.mainservice.repository.reservation;

import com.hotelium.mainservice.domain.reservation.ReservationTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

/**
 * @author Mete Aydin
 * @since 21.03.2022
 */
public interface ReservationTransactionRepository extends JpaRepository<ReservationTransaction, String>,
        JpaSpecificationExecutor<ReservationTransaction> {
    List<ReservationTransaction> findReservationTransactionsByReservationDateBetweenAndOrgIdAndReservationMasterRoomId(Date toDate, Date fromDate,
                                                                                                                       String orgId, String roomId);

    List<ReservationTransaction> findReservationTransactionsByReservationMasterId(String id);

    List<ReservationTransaction> findReservationTransactionsByReservationDateBetweenAndOrgId(Date toDate, Date fromDate,
                                                                                             String orgId);
}
