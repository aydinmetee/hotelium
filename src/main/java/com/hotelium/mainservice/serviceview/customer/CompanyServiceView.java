package com.hotelium.mainservice.serviceview.customer;

import com.hotelium.mainservice.dto.customer.CompanyReadDTO;
import com.hotelium.mainservice.dto.customer.CompanySearchCriteriaDTO;
import com.hotelium.mainservice.dto.customer.CompanyWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
public interface CompanyServiceView {
    CompanyReadDTO create(CompanyWriteDTO companyWriteDTO);

    CompanyReadDTO getById(String id);

    CompanyReadDTO update(String id, CompanyWriteDTO companyWriteDTO);

    CompanyReadDTO delete(String id);

    Page<CompanyReadDTO> search(CompanySearchCriteriaDTO filter, Pageable pageable);
}
