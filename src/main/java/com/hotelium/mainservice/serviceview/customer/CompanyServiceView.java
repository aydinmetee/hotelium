package com.hotelium.mainservice.serviceview.customer;

import com.hotelium.mainservice.domain.customer.Company;
import com.hotelium.mainservice.dto.customer.CompanyReadDTO;
import com.hotelium.mainservice.dto.customer.CompanySearchCriteriaDTO;
import com.hotelium.mainservice.dto.customer.CompanyWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface CompanyServiceView {
    CompanyReadDTO create(CompanyWriteDTO companyWriteDTO);

    CompanyReadDTO getById(Long id);

    CompanyReadDTO delete(Long id);

    Page<CompanyReadDTO> search(CompanySearchCriteriaDTO filter, Pageable pageable);
}
