package com.hotelium.mainservice.endpoint;

import com.hotelium.mainservice.annotation.NoSession;
import com.hotelium.mainservice.dto.OrganizationModelDTO;
import com.hotelium.mainservice.serviceview.OrganizationServiceView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mete Aydin
 * @since 18.03.2022
 */
@RestController
@RequestMapping("/org-def")
@RequiredArgsConstructor
@Api(value = "/org-def")
public class OrganizationController {
    private final OrganizationServiceView organizationServiceView;

    @NoSession
    @PostMapping()
    @ApiOperation(value = "Organization Def", response = OrganizationModelDTO.class)
    public ResponseEntity<OrganizationModelDTO> create(@RequestBody OrganizationModelDTO organizationModelDTO) {
        return ResponseEntity.ok(organizationServiceView.create(organizationModelDTO));
    }
}
