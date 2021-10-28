package com.hotelium.mainservice.service.reservation;

import com.hotelium.mainservice.domain.reservation.ReservationDetail;
import com.hotelium.mainservice.dto.reservation.ReservationDetailSearchCriteriaDTO;
import com.hotelium.mainservice.dto.reservation.ReservationDetailWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface ReservationDetailService {
    ReservationDetail create(ReservationDetailWriteDTO reservationDetailWriteDTO);

    ReservationDetail getById(Long id);

    ReservationDetail delete(Long id);

    Page<ReservationDetail> search(ReservationDetailSearchCriteriaDTO filter, Pageable pageable);
}
