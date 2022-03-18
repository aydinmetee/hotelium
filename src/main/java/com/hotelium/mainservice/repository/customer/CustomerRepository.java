package com.hotelium.mainservice.repository.customer;

import com.hotelium.mainservice.domain.customer.Customer;
import com.hotelium.mainservice.domain.reservation.ReservationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {
    Optional<Customer> findByIdAndOrgId(String id, String orgId);

}
