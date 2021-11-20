package com.hotelium.mainservice.service.reservation.impl;

import com.hotelium.mainservice.domain.reservation.ReservationDetail;
import com.hotelium.mainservice.dto.reservation.ReservationDetailSearchCriteriaDTO;
import com.hotelium.mainservice.dto.reservation.ReservationDetailWriteDTO;
import com.hotelium.mainservice.exception.ServiceExecutionException;
import com.hotelium.mainservice.repository.reservation.ReservationDetailRepository;
import com.hotelium.mainservice.service.customer.CustomerService;
import com.hotelium.mainservice.service.reservation.ReservationDetailService;
import com.hotelium.mainservice.service.reservation.ReservationMasterService;
import com.hotelium.mainservice.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Service
public class ReservationDetailServiceImpl implements ReservationDetailService {
    private ReservationDetailRepository reservationDetailRepository;
    private ReservationMasterService reservationMasterService;
    private CustomerService customerService;
    private MessageUtil messageUtil;

    @Autowired
    public ReservationDetailServiceImpl(ReservationDetailRepository reservationDetailRepository,
                                             @Lazy ReservationMasterService reservationMasterService,
                                             @Lazy CustomerService customerService, MessageUtil messageUtil) {
        this.reservationDetailRepository = reservationDetailRepository;
        this.reservationMasterService = reservationMasterService;
        this.customerService = customerService;
        this.messageUtil = messageUtil;
    }


    @Override
    public ReservationDetail create(ReservationDetailWriteDTO reservationDetailWriteDTO) {
        final var reservationDetail = new ReservationDetail();
        reservationDetail.setReservationMaster(reservationMasterService
                .getById(reservationDetailWriteDTO.getReservationMasterId()));
        reservationDetail.setCustomer(customerService.getById(reservationDetailWriteDTO.getCustomerId()));
        return reservationDetailRepository.save(reservationDetail);
    }

    @Override
    public ReservationDetail getById(Long id) {
        final var reservationDetail = reservationDetailRepository.findById(id);
        if (reservationDetail.isEmpty()) {
            throw new ServiceExecutionException(messageUtil.get("reservationDetail.recordNotFound.exception"));
        }
        return reservationDetail.get();
    }

    @Override
    public ReservationDetail delete(Long id) {
        final var deletedDetail = reservationDetailRepository.getOne(id);
        reservationDetailRepository.deleteById(id);
        return deletedDetail;
    }

    @Override
    public Page<ReservationDetail> search(ReservationDetailSearchCriteriaDTO filter, Pageable pageable) {
        return reservationDetailRepository.findAll(filter.ReservationDetailSearchCriteriaFieldMapper(filter), pageable);
    }
}
