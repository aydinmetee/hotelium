package com.hotelium.mainservice.endpoint;

import com.hotelium.mainservice.dto.reservation.ReservationBookingDTO;
import com.hotelium.mainservice.dto.reservation.ReservationDetailReadDTO;
import com.hotelium.mainservice.dto.reservation.ReservationDetailSearchCriteriaDTO;
import com.hotelium.mainservice.dto.reservation.ReservationDetailWriteDTO;
import com.hotelium.mainservice.dto.reservation.ReservationMasterReadDTO;
import com.hotelium.mainservice.dto.reservation.ReservationMasterSearchCriteriaDTO;
import com.hotelium.mainservice.dto.reservation.ReservationMasterWriteDTO;
import com.hotelium.mainservice.serviceview.reservation.ReservationDetailServiceView;
import com.hotelium.mainservice.serviceview.reservation.ReservationMasterServiceView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationMasterServiceView reservationMasterServiceView;
    private final ReservationDetailServiceView reservationDetailServiceView;

    @PostMapping()
    public ResponseEntity<ReservationMasterReadDTO> create(@RequestBody ReservationMasterWriteDTO reservationMasterWriteDTO) {
        return ResponseEntity.ok(reservationMasterServiceView.create(reservationMasterWriteDTO));
    }

    @GetMapping("/{masterId}")
    public ResponseEntity<ReservationMasterReadDTO> getById(@PathVariable("masterId") String masterId) {
        return ResponseEntity.ok(reservationMasterServiceView.getById(masterId));
    }

    @DeleteMapping("/{masterId}")
    public ResponseEntity<ReservationMasterReadDTO> delete(@PathVariable("masterId") String masterId) {
        return ResponseEntity.ok(reservationMasterServiceView.delete(masterId));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<ReservationMasterReadDTO>> search(@RequestBody() ReservationMasterSearchCriteriaDTO filter,
                                                                 Pageable pageable) {
        return ResponseEntity.ok(reservationMasterServiceView.search(filter, pageable));
    }

    @PostMapping("/{masterId}/mark-as-booking")
    public ResponseEntity<ReservationMasterReadDTO> markAsBooking(@RequestBody ReservationBookingDTO reservationBookingDTO,
                                                                  @PathVariable("masterId") String masterId) {
        reservationBookingDTO.setMasterId(masterId);
        return ResponseEntity.ok(reservationMasterServiceView.markAsBooking(reservationBookingDTO));
    }

    @GetMapping("/{masterId}/mark-as-completed")
    public ResponseEntity<ReservationMasterReadDTO> markAsCompleted(@PathVariable("masterId") String masterId) {
        return ResponseEntity.ok(reservationMasterServiceView.markAsComplete(masterId));
    }

    @PostMapping("/{masterId}/details")
    public ResponseEntity<ReservationDetailReadDTO> createDetail(@RequestBody ReservationDetailWriteDTO reservationDetailWriteDTO,
                                                                 @PathVariable("masterId") String masterId) {
        reservationDetailWriteDTO.setReservationMasterId(masterId);
        return ResponseEntity.ok(reservationDetailServiceView.create(reservationDetailWriteDTO));
    }

    @GetMapping("/{masterId}/details/{detailId}")
    public ResponseEntity<ReservationDetailReadDTO> getByIdDetail(@PathVariable("detailId") String detailId) {
        return ResponseEntity.ok(reservationDetailServiceView.getById(detailId));
    }

    @DeleteMapping("/{masterId}/details/{detailId}")
    public ResponseEntity<ReservationDetailReadDTO> deleteDetail(@PathVariable("detailId") String detailId) {
        return ResponseEntity.ok(reservationDetailServiceView.delete(detailId));
    }

    @PostMapping("/{masterId}/details/search")
    public ResponseEntity<Page<ReservationDetailReadDTO>> searchDetails(@RequestBody() ReservationDetailSearchCriteriaDTO filter,
                                                                        Pageable pageable) {
        return ResponseEntity.ok(reservationDetailServiceView.search(filter, pageable));
    }
}
