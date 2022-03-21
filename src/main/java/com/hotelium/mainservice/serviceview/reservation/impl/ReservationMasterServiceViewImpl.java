package com.hotelium.mainservice.serviceview.reservation.impl;

import com.hotelium.mainservice.domain.reservation.ReservationMaster;
import com.hotelium.mainservice.dto.reservation.ReservationBookingDTO;
import com.hotelium.mainservice.dto.reservation.ReservationMasterReadDTO;
import com.hotelium.mainservice.dto.reservation.ReservationMasterSearchCriteriaDTO;
import com.hotelium.mainservice.dto.reservation.ReservationMasterWriteDTO;
import com.hotelium.mainservice.service.reservation.ReservationMasterService;
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
    public List<ReservationMasterReadDTO> getWeeklyReservations() {
        return reservationMasterService.getWeeklyReservations().stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ReservationMasterReadDTO convertToDto(ReservationMaster reservationMaster) {
        final var readDTO = modelMapper.map(reservationMaster, ReservationMasterReadDTO.class);
        if (Objects.nonNull(reservationMaster.getAccountTransaction())) {
            readDTO.setAccountTransactionId(reservationMaster.getAccountTransaction().getId());
            readDTO.setBookAmount(reservationMaster.getAccountTransaction().getAmount());
            readDTO.setSource(reservationMaster.getAccountTransaction().getSource());
        }
        if (Objects.nonNull(reservationMaster.getRoom())) {
            readDTO.setRoomCode(reservationMaster.getRoom().getCode());
            readDTO.setRoomId(reservationMaster.getRoom().getId());
        }
        return readDTO;
    }
}
