package com.hotelium.mainservice.serviceview.reservation;

import com.hotelium.mainservice.dto.reservation.ReservationBookingDTO;
import com.hotelium.mainservice.dto.reservation.ReservationMasterReadDTO;
import com.hotelium.mainservice.dto.reservation.ReservationMasterSearchCriteriaDTO;
import com.hotelium.mainservice.dto.reservation.ReservationMasterWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

}
