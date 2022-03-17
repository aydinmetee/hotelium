package com.hotelium.mainservice.serviceview.customer;

import com.hotelium.mainservice.dto.customer.CustomerReadDTO;
import com.hotelium.mainservice.dto.customer.CustomerSearchCriteriaDTO;
import com.hotelium.mainservice.dto.customer.CustomerWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface CustomerServiceView {
    CustomerReadDTO create(CustomerWriteDTO customerWriteDTO);

    CustomerReadDTO getById(String id);

    CustomerReadDTO delete(String id);

    Page<CustomerReadDTO> search(CustomerSearchCriteriaDTO filter, Pageable pageable);

    CustomerReadDTO assignCompany(String id, String companyId);
}
