package com.hotelium.mainservice.dto.account;

import com.hotelium.mainservice.domain.AccountTransaction;
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
public class AccountTransactionSearchCriteriaDTO extends AccountTransactionReadDTO {
    private Date firstDate;
    private Date lastDate;

    public SearchCriteriaOptions<AccountTransaction> accountTransactionSearchCriteriaFieldMapper(AccountTransactionSearchCriteriaDTO filter) {
        final var searchCriteriaOptions = new SearchCriteriaOptions<AccountTransaction>();
        searchCriteriaOptions.add(new SearchCriteria("id", filter.getId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("type", filter.getType(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("amount", filter.getAmount(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("source", filter.getSource(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("description", filter.getDescription(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("creDate", filter.getFirstDate(), SearchCriteria.SearchOperation.GREATER_THAN_EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("creDate", filter.getLastDate(), SearchCriteria.SearchOperation.LESS_THAN_EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("orgId", filter.getOrgId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("creUser", filter.getCreUser(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("updUser", filter.getUpdUser(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("nameTitle", filter.getNameTitle(), SearchCriteria.SearchOperation.LIKE));

        return searchCriteriaOptions;
    }
}
