package com.hotelium.mainservice.service.customer;

import com.hotelium.mainservice.domain.customer.Company;
import com.hotelium.mainservice.dto.customer.CompanySearchCriteriaDTO;
import com.hotelium.mainservice.dto.customer.CompanyWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
public interface CompanyService {
    Company create(CompanyWriteDTO companyWriteDTO);

    Company getById(String id);

    Company update(String id, CompanyWriteDTO companyWriteDTO);

    Company delete(String id);

    Page<Company> search(CompanySearchCriteriaDTO filter, Pageable pageable);
}
