package com.hotelium.mainservice.service.reservation.impl;

import com.hotelium.mainservice.domain.reservation.ReservationMaster;
import com.hotelium.mainservice.domain.reservation.ReservationTransaction;
import com.hotelium.mainservice.dto.reservation.ResarvationPeriod;
import com.hotelium.mainservice.dto.reservation.ReservationMasterWriteDTO;
import com.hotelium.mainservice.exception.ServiceExecutionException;
import com.hotelium.mainservice.repository.reservation.ReservationTransactionRepository;
import com.hotelium.mainservice.service.reservation.ReservationTransactionService;
import com.hotelium.mainservice.util.DateUtil;
import com.hotelium.mainservice.util.SessionContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Mete Aydin
 * @since 21.03.2022
 */
@Service
@RequiredArgsConstructor
public class ReservationTransactionServiceImpl implements ReservationTransactionService {
    private final ReservationTransactionRepository reservationTransactionRepository;

    @Override
    @Transactional
    public void createAll(ReservationMaster reservationMaster) {
        Date lastDate = reservationMaster.getReservationDate();
        for (int index = 0; index < reservationMaster.getDuration().intValue(); index++) {
            final var reservationTransaction = new ReservationTransaction();
            reservationTransaction.setReservationDate(lastDate);
            reservationTransaction.setReservationMaster(reservationMaster);
            reservationTransactionRepository.save(reservationTransaction);
            lastDate = DateUtil.daysCalculator(lastDate, 1L);
        }
    }

    @Override
    public void checkRoomAvailability(ReservationMasterWriteDTO reservationMasterWriteDTO) {
        final var transactions = reservationTransactionRepository
                .findReservationTransactionsByReservationDateBetweenAndOrgIdAndReservationMasterRoomId(
                        reservationMasterWriteDTO.getReservationDate(),
                        DateUtil.daysCalculator(reservationMasterWriteDTO.getReservationDate(), reservationMasterWriteDTO.getDuration()),
                        SessionContext.getSessionData().getOrgId(),
                        reservationMasterWriteDTO.getRoomId()
                );
        if (!transactions.isEmpty()) {
            throw new ServiceExecutionException("Seçtiğiniz tarihte oda müsait değil.");
        }
    }

    @Override
    @Transactional
    public void clearAll(ReservationMaster reservationMaster) {
        final var transactions = reservationTransactionRepository
                .findReservationTransactionsByReservationMasterId(reservationMaster.getId());
        transactions.forEach(reservationTransaction -> {
            reservationTransactionRepository.deleteAll(transactions);
        });
    }

    @Override
    public List<ReservationTransaction> getResarvationTransaction(ResarvationPeriod resarvationPeriod) {
        Long period;
        if (ResarvationPeriod.MONTHLY.equals(resarvationPeriod)) {
            period = 29L;
        } else {
            period = 6L;
        }
        return reservationTransactionRepository.findReservationTransactionsByReservationDateBetweenAndOrgId(
                DateUtil.startOfDay(DateUtil.daysCalculator(new Date(),-1L)),
                DateUtil.endOfDay(DateUtil.daysCalculator(new Date(), period)),
                SessionContext.getSessionData().getOrgId());
    }
}
