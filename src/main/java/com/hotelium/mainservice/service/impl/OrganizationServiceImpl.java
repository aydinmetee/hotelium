package com.hotelium.mainservice.service.impl;

import com.hotelium.mainservice.domain.Organization;
import com.hotelium.mainservice.dto.OrganizationModelDTO;
import com.hotelium.mainservice.exception.ServiceExecutionException;
import com.hotelium.mainservice.repository.OrganizationRepository;
import com.hotelium.mainservice.service.OrganizationService;
import com.hotelium.mainservice.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @author Mete Aydin
 * @since 18.03.2022
 */
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final MessageUtil messageUtil;
    private final ModelMapper modelMapper;

    @Override
    public Organization create(OrganizationModelDTO organizationModelDTO) {
        final var existOrgList = organizationRepository.findAllByCode(organizationModelDTO.getCode());
        if (!existOrgList.isEmpty()) {
            throw new ServiceExecutionException(messageUtil.get("validation.orgDef.codeAlreadyExist"));
        }
        final var orgDb = modelMapper.map(organizationModelDTO, Organization.class);
        return organizationRepository.save(orgDb);
    }
}
