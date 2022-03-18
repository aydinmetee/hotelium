package com.hotelium.mainservice.serviceview.impl;

import com.hotelium.mainservice.dto.OrganizationModelDTO;
import com.hotelium.mainservice.service.OrganizationService;
import com.hotelium.mainservice.serviceview.OrganizationServiceView;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @author Mete Aydin
 * @since 18.03.2022
 */
@Service
@RequiredArgsConstructor
public class OrganizationServiceViewImpl implements OrganizationServiceView {
    private final OrganizationService organizationService;
    private final ModelMapper modelMapper;

    @Override
    public OrganizationModelDTO create(OrganizationModelDTO organizationModelDTO) {
        return modelMapper.map(organizationService.create(organizationModelDTO), OrganizationModelDTO.class);
    }
}
