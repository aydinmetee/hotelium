package com.hotelium.mainservice.service.reservation;

import com.hotelium.mainservice.domain.reservation.ReservationMaster;
import com.hotelium.mainservice.domain.reservation.ReservationTransaction;
import com.hotelium.mainservice.dto.reservation.ResarvationPeriod;
import com.hotelium.mainservice.dto.reservation.ReservationMasterWriteDTO;

import java.util.List;

/**
 * @author Mete Aydin
 * @since 21.03.2022
 */
public interface ReservationTransactionService {
    void createAll(ReservationMaster reservationMaster);

    void checkRoomAvailability(ReservationMasterWriteDTO reservationMasterWriteDTO);

    void clearAll(ReservationMaster reservationMaster);

    List<ReservationTransaction> getResarvationTransaction(ResarvationPeriod resarvationPeriod);
}
