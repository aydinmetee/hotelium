package com.hotelium.mainservice.dto.customer;

import com.hotelium.mainservice.domain.customer.Customer;
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
public class CustomerSearchCriteriaDTO extends CustomerReadDTO {
    public SearchCriteriaOptions<Customer> CustomerSearchCriteriaFieldMapper(CustomerSearchCriteriaDTO filter) {
        final var searchCriteriaOptions = new SearchCriteriaOptions<Customer>();
        searchCriteriaOptions.add(new SearchCriteria("id", filter.getId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("companyName", filter.getCompanyName(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("name", filter.getName(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("lastname", filter.getLastname(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("legalId", filter.getLegalId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("phone", filter.getPhone(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("orgId",filter.getOrgId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("creUser",filter.getCreUser(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("updUser",filter.getUpdUser(), SearchCriteria.SearchOperation.EQUAL));

        return searchCriteriaOptions;
    }
}
