package com.hotelium.mainservice.serviceview.reservation.impl;

import com.hotelium.mainservice.domain.reservation.ReservationDetail;
import com.hotelium.mainservice.dto.reservation.ReservationDetailReadDTO;
import com.hotelium.mainservice.dto.reservation.ReservationDetailSearchCriteriaDTO;
import com.hotelium.mainservice.dto.reservation.ReservationDetailWriteDTO;
import com.hotelium.mainservice.service.reservation.ReservationDetailService;
import com.hotelium.mainservice.serviceview.reservation.ReservationDetailServiceView;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@RequiredArgsConstructor
@Service
public class ReservationDetailServiceViewImpl implements ReservationDetailServiceView {
    private final ReservationDetailService reservationDetailService;
    private final ModelMapper modelMapper;

    @Override
    public ReservationDetailReadDTO create(ReservationDetailWriteDTO reservationDetailWriteDTO) {
        return convertToDto(reservationDetailService.create(reservationDetailWriteDTO));
    }

    @Override
    public ReservationDetailReadDTO getById(String id) {
        return convertToDto(reservationDetailService.getById(id));
    }

    @Override
    public ReservationDetailReadDTO delete(String id) {
        return convertToDto(reservationDetailService.delete(id));
    }

    @Override
    public Page<ReservationDetailReadDTO> search(ReservationDetailSearchCriteriaDTO filter, Pageable pageable) {
        return reservationDetailService.search(filter, pageable).map(this::convertToDto);
    }

    private ReservationDetailReadDTO convertToDto(ReservationDetail reservationDetail) {
        final var readDto = modelMapper.map(reservationDetail, ReservationDetailReadDTO.class);
        readDto.setReservationMasterId(reservationDetail.getReservationMaster().getId());
        readDto.setCustomerId(reservationDetail.getCustomer().getId());
        readDto.setCustomerFullName(reservationDetail.getCustomer().getName() + " " + reservationDetail.getCustomer().getLastname());
        readDto.setCustomerLegalId(reservationDetail.getCustomer().getLegalId());
        readDto.setCustomerPhone(reservationDetail.getCustomer().getPhone());
        return readDto;
    }
}
