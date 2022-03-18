package com.hotelium.mainservice.repository;

import com.hotelium.mainservice.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, String> {
    List<Organization> findAllByCode(String code);
}
