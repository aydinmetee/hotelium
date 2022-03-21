package com.hotelium.mainservice.serviceview.reservation;

import com.hotelium.mainservice.dto.reservation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface ReservationMasterServiceView {
    ReservationMasterReadDTO create(ReservationMasterWriteDTO reservationMasterWriteDTO);

    ReservationMasterReadDTO getById(String id);

    ReservationMasterReadDTO delete(String id);

    Page<ReservationMasterReadDTO> search(ReservationMasterSearchCriteriaDTO filter, Pageable pageable);

    ReservationMasterReadDTO markAsBooking(ReservationBookingDTO reservationBookingDTO);

    ReservationMasterReadDTO markAsComplete(String id);

    List<ResarvationTransactionReadDTO> getWeeklyReservations(ResarvationPeriod resarvationPeriod);

    ReservationMasterReadDTO markAsCancelled(String id);

}
