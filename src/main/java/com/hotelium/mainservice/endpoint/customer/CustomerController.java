package com.hotelium.mainservice.endpoint.customer;

import com.hotelium.mainservice.dto.customer.CustomerReadDTO;
import com.hotelium.mainservice.dto.customer.CustomerSearchCriteriaDTO;
import com.hotelium.mainservice.dto.customer.CustomerWriteDTO;
import com.hotelium.mainservice.serviceview.customer.CustomerServiceView;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerServiceView customerServiceView;

    @PostMapping()
    public ResponseEntity<CustomerReadDTO> create(@RequestBody CustomerWriteDTO customerWriteDTO) {
        return ResponseEntity.ok(customerServiceView.create(customerWriteDTO));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerReadDTO> getById(@PathVariable("customerId") String customerId) {
        return ResponseEntity.ok(customerServiceView.getById(customerId));
    }

    @PutMapping("/{customerId}")
    @ApiOperation(value = "Update Customer", response = CustomerReadDTO.class)
    public ResponseEntity<CustomerReadDTO> update(@PathVariable("customerId") String customerId,
                                                  @RequestBody CustomerWriteDTO customerWriteDTO) {
        return ResponseEntity.ok(customerServiceView.update(customerId, customerWriteDTO));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<CustomerReadDTO> delete(@PathVariable("customerId") String customerId) {
        return ResponseEntity.ok(customerServiceView.delete(customerId));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<CustomerReadDTO>> search(@RequestBody() CustomerSearchCriteriaDTO filter,
                                                        Pageable pageable) {
        return ResponseEntity.ok(customerServiceView.search(filter, pageable));
    }

    @GetMapping("/{customerId}/assign-company/{companyId}")
    public ResponseEntity<CustomerReadDTO> getById(@PathVariable("customerId") String customerId,
                                                   @PathVariable("companyId") String companyId) {
        return ResponseEntity.ok(customerServiceView.assignCompany(customerId, companyId));
    }
}
