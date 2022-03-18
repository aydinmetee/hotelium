package com.hotelium.mainservice.service;

import com.hotelium.mainservice.domain.Organization;
import com.hotelium.mainservice.dto.OrganizationModelDTO;

/**
 * @author Mete Aydin
 * @since 18.03.2022
 */
public interface OrganizationService {
    Organization create(OrganizationModelDTO organizationModelDTO);
}
