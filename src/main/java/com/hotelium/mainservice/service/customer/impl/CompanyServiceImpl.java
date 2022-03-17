package com.hotelium.mainservice.service.customer.impl;

import com.hotelium.mainservice.domain.customer.Company;
import com.hotelium.mainservice.dto.customer.CompanySearchCriteriaDTO;
import com.hotelium.mainservice.dto.customer.CompanyWriteDTO;
import com.hotelium.mainservice.exception.ServiceExecutionException;
import com.hotelium.mainservice.repository.customer.CompanyRepository;
import com.hotelium.mainservice.service.customer.CompanyService;
import com.hotelium.mainservice.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final ModelMapper modelMapper;
    private final CompanyRepository companyRepository;
    private final MessageUtil messageUtil;

    @Override
    public Company create(CompanyWriteDTO companyWriteDTO) {
        final var companyDb = modelMapper.map(companyWriteDTO, Company.class);
        return companyRepository.save(companyDb);
    }

    @Override
    public Company getById(String id) {
        final var company = companyRepository.findById(id);
        if (company.isEmpty()) {
            throw new ServiceExecutionException(messageUtil.get("customer.companyNotFound.exception"));
        }
        return company.get();
    }

    @Override
    public Company delete(String id) {
        final var deletedCompany = getById(id);
        companyRepository.deleteById(id);
        return deletedCompany;
    }

    @Override
    public Page<Company> search(CompanySearchCriteriaDTO filter, Pageable pageable) {
        return companyRepository.findAll(filter.CompanySearchCriteriaFieldMapper(filter), pageable);
    }
}
