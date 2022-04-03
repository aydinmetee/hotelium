package com.hotelium.mainservice.repository.customer;

import com.hotelium.mainservice.domain.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {
    Optional<Customer> findByIdAndOrgId(String id, String orgId);

    Page<Customer> getCustomersByNameContainsOrLastnameContainsAndOrgId(String name, String lastName, String orgId, Pageable pageable);
}
