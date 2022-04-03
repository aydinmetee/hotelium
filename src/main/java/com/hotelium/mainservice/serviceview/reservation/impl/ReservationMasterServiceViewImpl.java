package com.hotelium.mainservice.serviceview.reservation.impl;

import com.hotelium.mainservice.domain.reservation.ReservationMaster;
import com.hotelium.mainservice.domain.reservation.ReservationTransaction;
import com.hotelium.mainservice.dto.reservation.*;
import com.hotelium.mainservice.service.reservation.ReservationMasterService;
import com.hotelium.mainservice.service.reservation.ReservationTransactionService;
import com.hotelium.mainservice.serviceview.reservation.ReservationMasterServiceView;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@RequiredArgsConstructor
@Service
public class ReservationMasterServiceViewImpl implements ReservationMasterServiceView {
    private final ReservationMasterService reservationMasterService;
    private final ModelMapper modelMapper;
    private final ReservationTransactionService reservationTransactionService;

    @Override
    public ReservationMasterReadDTO create(ReservationMasterWriteDTO reservationMasterWriteDTO) {
        return convertToDto(reservationMasterService.create(reservationMasterWriteDTO));
    }

    @Override
    public ReservationMasterReadDTO getById(String id) {
        return convertToDto(reservationMasterService.getById(id));
    }

    @Override
    public ReservationMasterReadDTO delete(String id) {
        return convertToDto(reservationMasterService.delete(id));
    }

    @Override
    public Page<ReservationMasterReadDTO> search(ReservationMasterSearchCriteriaDTO filter, Pageable pageable) {
        return reservationMasterService.search(filter, pageable).map(this::convertToDto);
    }

    @Override
    public ReservationMasterReadDTO markAsBooking(ReservationBookingDTO reservationBookingDTO) {
        return convertToDto(reservationMasterService.markAsBooking(reservationBookingDTO));
    }

    @Override
    public ReservationMasterReadDTO markAsComplete(String id) {
        return convertToDto(reservationMasterService.markAsComplete(id));
    }

    @Override
    public List<ResarvationTransactionReadDTO> getWeeklyReservations(ResarvationPeriod resarvationPeriod) {
        return reservationTransactionService.getResarvationTransaction(resarvationPeriod).stream().map(this::convertTransactionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationMasterReadDTO markAsCancelled(String id) {
        return convertToDto(reservationMasterService.markAsCancelled(id));
    }

    @Override
    public ReservationMasterReadDTO getPayment(ReservationPaymentDTO reservationPaymentDTO) {
        return convertToDto(reservationMasterService.getPayment(reservationPaymentDTO));
    }

    private ReservationMasterReadDTO convertToDto(ReservationMaster reservationMaster) {
        final var readDTO = modelMapper.map(reservationMaster, ReservationMasterReadDTO.class);
        if (Objects.nonNull(reservationMaster.getRoom())) {
            readDTO.setRoomCode(reservationMaster.getRoom().getCode());
            readDTO.setRoomId(reservationMaster.getRoom().getId());
        }
        return readDTO;
    }

    private ResarvationTransactionReadDTO convertTransactionDTO(ReservationTransaction reservationTransaction) {
        final var readDTO = new ResarvationTransactionReadDTO();
        readDTO.setId(reservationTransaction.getId());
        readDTO.setRoomCode(reservationTransaction.getReservationMaster().getRoom().getCode());
        readDTO.setResarvationDate(reservationTransaction.getReservationDate());
        return readDTO;
    }
}
