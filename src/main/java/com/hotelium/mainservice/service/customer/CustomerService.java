package com.hotelium.mainservice.service.customer;

import com.hotelium.mainservice.domain.customer.Customer;
import com.hotelium.mainservice.dto.customer.CustomerSearchCriteriaDTO;
import com.hotelium.mainservice.dto.customer.CustomerWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface CustomerService {
    Customer create(CustomerWriteDTO customerWriteDTO);

    Customer getById(Long id);

    Customer delete(Long id);

    Page<Customer> search(CustomerSearchCriteriaDTO filter, Pageable pageable);

    Customer assignCompany(Long id, Long companyId);
}
