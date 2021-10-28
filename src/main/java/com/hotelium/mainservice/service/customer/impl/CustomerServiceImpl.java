package com.hotelium.mainservice.service.customer.impl;

import com.hotelium.mainservice.domain.customer.Customer;
import com.hotelium.mainservice.dto.customer.CustomerSearchCriteriaDTO;
import com.hotelium.mainservice.dto.customer.CustomerWriteDTO;
import com.hotelium.mainservice.exception.ServiceExecutionException;
import com.hotelium.mainservice.repository.customer.CustomerRepository;
import com.hotelium.mainservice.service.customer.CompanyService;
import com.hotelium.mainservice.service.customer.CustomerService;
import com.hotelium.mainservice.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
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
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final MessageUtil messageUtil;
    @Lazy
    private final CompanyService companyService;

    @Override
    public Customer create(CustomerWriteDTO customerWriteDTO) {
        final var customerDB = modelMapper.map(customerWriteDTO, Customer.class);
        return customerRepository.save(customerDB);
    }

    @Override
    public Customer getById(Long id) {
        final var customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new ServiceExecutionException(messageUtil.get("reservationDetail.customerNotFound.exception"));
        }
        return customer.get();
    }

    @Override
    public Customer delete(Long id) {
        final var deletedCustomer = getById(id);
        customerRepository.deleteById(id);
        return deletedCustomer;
    }

    @Override
    public Page<Customer> search(CustomerSearchCriteriaDTO filter, Pageable pageable) {
        return customerRepository.findAll(filter.CustomerSearchCriteriaFieldMapper(filter), pageable);
    }

    @Override
    public Customer assignCompany(Long id, Long companyId) {
        final var company = companyService.getById(companyId);
        final var customerDb = getById(id);
        customerDb.setCompany(company);
        return customerRepository.save(customerDb);
    }
}
