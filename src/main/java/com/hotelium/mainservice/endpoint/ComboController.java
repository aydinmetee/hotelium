package com.hotelium.mainservice.endpoint;

import com.hotelium.mainservice.dto.RoomSearchCriteriaDTO;
import com.hotelium.mainservice.dto.customer.CompanySearchCriteriaDTO;
import com.hotelium.mainservice.dto.customer.CustomerSearchCriteriaDTO;
import com.hotelium.mainservice.dto.reservation.ReservationDetailSearchCriteriaDTO;
import com.hotelium.mainservice.serviceview.RoomServiceView;
import com.hotelium.mainservice.serviceview.customer.CompanyServiceView;
import com.hotelium.mainservice.serviceview.customer.CustomerServiceView;
import com.hotelium.mainservice.serviceview.reservation.ReservationDetailServiceView;
import com.hotelium.mainservice.util.KeyValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/combo")
@RequiredArgsConstructor
@Api(value = "/combo")
public class ComboController {
    private final RoomServiceView roomServiceView;
    private final CompanyServiceView companyServiceView;
    private final CustomerServiceView customerServiceView;
    private final ReservationDetailServiceView reservationDetailServiceView;

    @PostMapping("/rooms")
    @ApiOperation(value = "Rooms Combo", response = Page.class)
    public ResponseEntity<List<KeyValue>> rooms(@RequestBody() RoomSearchCriteriaDTO filter) {
        final var pagingResult = roomServiceView.search(filter, PageRequest.of(0, 1000, Sort.by(Sort.Order.asc("code"))));
        List<KeyValue> keyValues = new ArrayList<>();
        if (pagingResult.hasContent()) {
            pagingResult.getContent().forEach(roomReadDTO -> keyValues.add(new KeyValue(roomReadDTO.getCode(), roomReadDTO.getId())));
        }
        return new ResponseEntity<>(keyValues, HttpStatus.OK);
    }

    @PostMapping("/companys")
    @ApiOperation(value = "Company Combo", response = Page.class)
    public ResponseEntity<List<KeyValue>> companys(@RequestBody() CompanySearchCriteriaDTO filter) {
        final var pagingResult = companyServiceView.search(filter, PageRequest.of(0, 1000));
        List<KeyValue> keyValues = new ArrayList<>();
        if (pagingResult.hasContent()) {
            pagingResult.getContent().forEach(companyReadDTO -> keyValues.add(new KeyValue(companyReadDTO.getNameTitle(), companyReadDTO.getId())));
        }
        return new ResponseEntity<>(keyValues, HttpStatus.OK);
    }

    @PostMapping("/customers")
    @ApiOperation(value = "Company Combo", response = Page.class)
    public ResponseEntity<List<KeyValue>> customers(@RequestBody() CustomerSearchCriteriaDTO filter) {
        final var pagingResult = customerServiceView.search(filter, PageRequest.of(0, 1000));
        List<KeyValue> keyValues = new ArrayList<>();
        if (pagingResult.hasContent()) {
            pagingResult.getContent().forEach(customerReadDTO -> keyValues.add(new KeyValue(customerReadDTO.getName() + " " + customerReadDTO.getLastname(), customerReadDTO.getId())));
        }
        return new ResponseEntity<>(keyValues, HttpStatus.OK);
    }

    @GetMapping("/drawee/{reservationMasterId}")
    @ApiOperation(value = "Company Combo", response = Page.class)
    public ResponseEntity<List<KeyValue>> drawees(@PathVariable("reservationMasterId") String reservationMasterId) {
        final var filter = new ReservationDetailSearchCriteriaDTO();
        filter.setReservationMasterId(reservationMasterId);
        final var pagingResult = reservationDetailServiceView
                .search(filter, PageRequest.of(0, 1000));
        List<KeyValue> keyValues = new ArrayList<>();
        if (pagingResult.hasContent()) {
            pagingResult.getContent().forEach(reservationDetailReadDTO -> {
                keyValues.add(
                        new KeyValue(reservationDetailReadDTO.getCustomerFullName() + " - " + reservationDetailReadDTO.getCustomerLegalId(),
                                reservationDetailReadDTO.getCustomerId(), "PERSON"));
                if (!StringUtils.isEmpty(reservationDetailReadDTO.getCompanyId())) {
                    var company = new KeyValue(reservationDetailReadDTO.getCompanyName() + " - " + reservationDetailReadDTO.getCompanyLegalNo(),
                            reservationDetailReadDTO.getCompanyId(), "LEGAL");
                    if (!keyValues.contains(company)) {
                        keyValues.add(company);
                    }
                }
            });
        }
        return new ResponseEntity<>(keyValues, HttpStatus.OK);
    }
}
