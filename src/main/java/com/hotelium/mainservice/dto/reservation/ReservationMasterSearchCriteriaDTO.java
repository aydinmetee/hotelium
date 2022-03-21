package com.hotelium.mainservice.dto.reservation;

import com.hotelium.mainservice.domain.reservation.ReservationMaster;
import com.hotelium.mainservice.util.SearchCriteria;
import com.hotelium.mainservice.util.SearchCriteriaOptions;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class ReservationMasterSearchCriteriaDTO extends ReservationMasterReadDTO {
    private Date firstDate;
    private Date lastDate;
    public SearchCriteriaOptions<ReservationMaster> ReservationMasterSearchCriteriaFieldMapper(ReservationMasterSearchCriteriaDTO filter) {
        final var searchCriteriaOptions = new SearchCriteriaOptions<ReservationMaster>();
        searchCriteriaOptions.add(new SearchCriteria("id", filter.getId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("status", filter.getStatus(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("accountTransaction/id", filter.getAccountTransactionId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("room/id", filter.getRoomId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("room/code", filter.getRoomCode(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("checkInDate", filter.getCheckInDate(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("checkOutDate", filter.getCheckOutDate(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("description", filter.getDescription(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("accountTransaction/amount", filter.getBookAmount(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("accountTransaction/source", filter.getSource(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("orgId",filter.getOrgId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("creUser",filter.getCreUser(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("updUser",filter.getUpdUser(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("reservationDate", filter.getReservationDate(), SearchCriteria.SearchOperation.GREATER_THAN_EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("reservationDate", filter.getReservationDate(), SearchCriteria.SearchOperation.LESS_THAN_EQUAL));

        return searchCriteriaOptions;
    }
}
