package com.hotelium.mainservice.repository.customer;

import com.hotelium.mainservice.domain.customer.Company;
import com.hotelium.mainservice.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface CompanyRepository extends JpaRepository<Company, String>, JpaSpecificationExecutor<Company> {
    Optional<Company> findByIdAndOrgId(String id, String orgId);

}
