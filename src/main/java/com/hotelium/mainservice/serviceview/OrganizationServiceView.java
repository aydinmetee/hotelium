package com.hotelium.mainservice.serviceview;

import com.hotelium.mainservice.dto.OrganizationModelDTO;

/**
 * @author Mete Aydin
 * @since 18.03.2022
 */
public interface OrganizationServiceView {
    OrganizationModelDTO create(OrganizationModelDTO organizationModelDTO);
}
