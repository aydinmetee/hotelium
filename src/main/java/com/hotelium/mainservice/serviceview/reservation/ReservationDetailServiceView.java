package com.hotelium.mainservice.serviceview.reservation;

import com.hotelium.mainservice.dto.reservation.ReservationDetailReadDTO;
import com.hotelium.mainservice.dto.reservation.ReservationDetailSearchCriteriaDTO;
import com.hotelium.mainservice.dto.reservation.ReservationDetailWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface ReservationDetailServiceView {
    ReservationDetailReadDTO create(ReservationDetailWriteDTO reservationDetailWriteDTO);

    ReservationDetailReadDTO getById(String id);

    ReservationDetailReadDTO delete(String id);

    Page<ReservationDetailReadDTO> search(ReservationDetailSearchCriteriaDTO filter, Pageable pageable);
}
