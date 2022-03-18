package com.hotelium.mainservice.service.reservation.impl;

import com.hotelium.mainservice.domain.AccountTransaction;
import com.hotelium.mainservice.domain.Room;
import com.hotelium.mainservice.domain.reservation.ReservationMaster;
import com.hotelium.mainservice.dto.account.AccountTransactionWriteDTO;
import com.hotelium.mainservice.dto.reservation.ReservationBookingDTO;
import com.hotelium.mainservice.dto.reservation.ReservationDetailSearchCriteriaDTO;
import com.hotelium.mainservice.dto.reservation.ReservationMasterSearchCriteriaDTO;
import com.hotelium.mainservice.dto.reservation.ReservationMasterWriteDTO;
import com.hotelium.mainservice.exception.ServiceExecutionException;
import com.hotelium.mainservice.repository.reservation.ReservationMasterRepository;
import com.hotelium.mainservice.service.AccountTransactionService;
import com.hotelium.mainservice.service.RoomService;
import com.hotelium.mainservice.service.reservation.ReservationDetailService;
import com.hotelium.mainservice.service.reservation.ReservationMasterService;
import com.hotelium.mainservice.util.MessageUtil;
import com.hotelium.mainservice.util.SessionContext;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @author Mete Aydin
 * date 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class ReservationMasterServiceImpl implements ReservationMasterService {
    @Lazy
    private final ReservationMasterRepository reservationMasterRepository;
    @Lazy
    private final ReservationDetailService reservationDetailService;
    private final ModelMapper modelMapper;
    private final RoomService roomService;
    private final AccountTransactionService accountTransactionService;
    private final MessageUtil messageUtil;

    @Override
    public ReservationMaster create(ReservationMasterWriteDTO reservationMasterWriteDTO) {
        final var room = roomService.getById(reservationMasterWriteDTO.getRoomId());
        if (Objects.isNull(room)) {
            throw new ServiceExecutionException(messageUtil.get("reservationMaster.roomNotFound.exception"));
        }
        checkRoomStatus(room);
        roomService.markAsReserved(room.getId());
        final var master = modelMapper.map(reservationMasterWriteDTO, ReservationMaster.class);
        master.setStatus(ReservationMaster.ReservationStatus.NEW);
        master.setRoom(room);
        return reservationMasterRepository.save(master);
    }

    @Override
    public ReservationMaster getById(String id) {
        final var master = reservationMasterRepository.findByIdAndOrgId(id,
                SessionContext.getSessionData().getOrgId());
        if (master.isEmpty()) {
            throw new ServiceExecutionException(messageUtil.get("reservationDetail.masterNotFound.exception"));
        }
        return master.get();
    }

    @Override
    public ReservationMaster delete(String id) {
        final var deletedMaster = reservationMasterRepository.getOne(id);
        reservationMasterRepository.deleteById(id);
        return deletedMaster;
    }

    @Override
    public Page<ReservationMaster> search(ReservationMasterSearchCriteriaDTO filter, Pageable pageable) {
        filter.setOrgId(SessionContext.getSessionData().getOrgId());
        return reservationMasterRepository.findAll(filter.ReservationMasterSearchCriteriaFieldMapper(filter), pageable);
    }

    @Override
    public ReservationMaster markAsBooking(ReservationBookingDTO reservationBookingDTO) {
        final var reservationMaster = getById(reservationBookingDTO.getMasterId());
        reservationMaster.setCheckInDate(new Date());
        checkHasMasterGotDetail(reservationMaster);
        final var accountTransaction = new AccountTransactionWriteDTO();
        accountTransaction.setAmount(reservationBookingDTO.getAmount());
        accountTransaction.setSource(reservationBookingDTO.getSource());
        accountTransaction.setType(AccountTransaction.TransactionType.INCOME);
        accountTransaction.setDescription(reservationMaster.getRoom().getCode() + " KONAKLAMA BEDELİ");
        reservationMaster.setAccountTransaction(accountTransactionService.create(accountTransaction));
        reservationMaster.setStatus(ReservationMaster.ReservationStatus.BOOKING);
        roomService.markAsFilled(reservationMaster.getRoom().getId());
        return reservationMasterRepository.save(reservationMaster);
    }

    @Override
    public ReservationMaster markAsComplete(String id) {
        final var reservationMaster = getById(id);
        reservationMaster.setStatus(ReservationMaster.ReservationStatus.COMPLETED);
        reservationMaster.setCheckOutDate(new Date());
        roomService.markAsDirt(reservationMaster.getRoom().getId());
        return reservationMasterRepository.save(reservationMaster);
    }

    private void checkRoomStatus(Room room) {
        if (!Room.RoomStatus.CLEAN.equals(room.getStatus())) {
            throw new ServiceExecutionException(messageUtil.get("reservationMaster.roomStatus.exception"));
        }
    }

    private void checkHasMasterGotDetail(ReservationMaster reservationMaster) {
        final var detailFilter = new ReservationDetailSearchCriteriaDTO();
        detailFilter.setReservationMasterId(reservationMaster.getId());
        final var details = reservationDetailService.search(detailFilter, PageRequest.of(0, 1));
        if (!details.hasContent()) {
            throw new ServiceExecutionException(messageUtil.get("reservationMaster.detailNotFound.exception"));
        }
    }

}
