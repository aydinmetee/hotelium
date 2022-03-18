package com.hotelium.mainservice.serviceview.customer.impl;

import com.hotelium.mainservice.domain.customer.Company;
import com.hotelium.mainservice.dto.customer.CompanyReadDTO;
import com.hotelium.mainservice.dto.customer.CompanySearchCriteriaDTO;
import com.hotelium.mainservice.dto.customer.CompanyWriteDTO;
import com.hotelium.mainservice.service.customer.CompanyService;
import com.hotelium.mainservice.serviceview.customer.CompanyServiceView;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@RequiredArgsConstructor
@Service
public class CompanyServiceViewImpl implements CompanyServiceView {
    private final CompanyService companyService;
    private final ModelMapper modelMapper;


    private CompanyReadDTO convertToDto(Company company) {
        return modelMapper.map(company, CompanyReadDTO.class);
    }

    @Override
    public CompanyReadDTO create(CompanyWriteDTO companyWriteDTO) {
        return convertToDto(companyService.create(companyWriteDTO));
    }

    @Override
    public CompanyReadDTO getById(String id) {
        return convertToDto(companyService.getById(id));
    }

    @Override
    public CompanyReadDTO update(String id, CompanyWriteDTO companyWriteDTO) {
        return convertToDto(companyService.update(id, companyWriteDTO));
    }

    @Override
    public CompanyReadDTO delete(String id) {
        return convertToDto(companyService.delete(id));
    }

    @Override
    public Page<CompanyReadDTO> search(CompanySearchCriteriaDTO filter, Pageable pageable) {
        return companyService.search(filter, pageable).map(this::convertToDto);
    }
}
