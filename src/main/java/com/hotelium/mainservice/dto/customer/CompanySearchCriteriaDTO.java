package com.hotelium.mainservice.dto.customer;

import com.hotelium.mainservice.domain.customer.Company;
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
public class CompanySearchCriteriaDTO extends CompanyReadDTO {
    public SearchCriteriaOptions<Company> CompanySearchCriteriaFieldMapper(CompanySearchCriteriaDTO filter) {
        final var searchCriteriaOptions = new SearchCriteriaOptions<Company>();
        searchCriteriaOptions.add(new SearchCriteria("id", filter.getId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("nameTitle", filter.getNameTitle(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("address", filter.getAddress(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("legalNo", filter.getLegalNo(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("taxOffice", filter.getTaxOffice(), SearchCriteria.SearchOperation.LIKE));

        return searchCriteriaOptions;
    }
}
