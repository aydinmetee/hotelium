package com.hotelium.mainservice.service.reservation;

import com.hotelium.mainservice.domain.reservation.ReservationMaster;
import com.hotelium.mainservice.dto.reservation.ReservationBookingDTO;
import com.hotelium.mainservice.dto.reservation.ReservationMasterSearchCriteriaDTO;
import com.hotelium.mainservice.dto.reservation.ReservationMasterWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface ReservationMasterService {
    ReservationMaster create(ReservationMasterWriteDTO reservationMasterWriteDTO);

    ReservationMaster getById(String id);

    ReservationMaster delete(String id);

    Page<ReservationMaster> search(ReservationMasterSearchCriteriaDTO filter, Pageable pageable);

    ReservationMaster markAsBooking(ReservationBookingDTO reservationBookingDTO);

    ReservationMaster markAsComplete(String id);

}
