package com.hotelium.mainservice.serviceview.customer.impl;

import com.hotelium.mainservice.domain.customer.Customer;
import com.hotelium.mainservice.dto.customer.CustomerReadDTO;
import com.hotelium.mainservice.dto.customer.CustomerSearchCriteriaDTO;
import com.hotelium.mainservice.dto.customer.CustomerWriteDTO;
import com.hotelium.mainservice.service.customer.CustomerService;
import com.hotelium.mainservice.serviceview.customer.CustomerServiceView;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceViewImpl implements CustomerServiceView {
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @Override
    public CustomerReadDTO create(CustomerWriteDTO customerWriteDTO) {
        return convertToDTO(customerService.create(customerWriteDTO));
    }

    @Override
    public CustomerReadDTO getById(String id) {
        return convertToDTO(customerService.getById(id));
    }

    @Override
    public CustomerReadDTO delete(String id) {
        return convertToDTO(customerService.delete(id));
    }

    @Override
    public Page<CustomerReadDTO> search(CustomerSearchCriteriaDTO filter, Pageable pageable) {
        return customerService.search(filter, pageable).map(this::convertToDTO);
    }

    @Override
    public CustomerReadDTO assignCompany(String id, String companyId) {
        return convertToDTO(customerService.assignCompany(id, companyId));
    }

    private CustomerReadDTO convertToDTO(Customer customer) {
        final var customerReadDTO = modelMapper.map(customer, CustomerReadDTO.class);
        if (Objects.nonNull(customer.getCompany())) {
            customerReadDTO.setCompanyName(customer.getCompany().getNameTitle());
        }
        return customerReadDTO;
    }
}
