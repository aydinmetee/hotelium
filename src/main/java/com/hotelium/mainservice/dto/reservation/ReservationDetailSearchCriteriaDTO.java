package com.hotelium.mainservice.dto.reservation;

import com.hotelium.mainservice.domain.reservation.ReservationDetail;
import com.hotelium.mainservice.util.SearchCriteria;
import com.hotelium.mainservice.util.SearchCriteriaOptions;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class ReservationDetailSearchCriteriaDTO extends ReservationDetailReadDTO {
    public SearchCriteriaOptions<ReservationDetail> ReservationDetailSearchCriteriaFieldMapper(ReservationDetailSearchCriteriaDTO filter) {
        final var searchCriteriaOptions = new SearchCriteriaOptions<ReservationDetail>();
        searchCriteriaOptions.add(new SearchCriteria("id", filter.getId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("customerFullName", filter.getCustomerFullName(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("reservationMaster/id", filter.getReservationMasterId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("customer/id", filter.getCustomerId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("customer/phone", filter.getCustomerPhone(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("customer/legalId", filter.getCustomerLegalId(), SearchCriteria.SearchOperation.LIKE));

        return searchCriteriaOptions;
    }
}
