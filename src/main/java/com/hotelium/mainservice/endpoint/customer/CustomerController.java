package com.hotelium.mainservice.endpoint.customer;

import com.hotelium.mainservice.dto.customer.CustomerReadDTO;
import com.hotelium.mainservice.dto.customer.CustomerSearchCriteriaDTO;
import com.hotelium.mainservice.dto.customer.CustomerWriteDTO;
import com.hotelium.mainservice.serviceview.customer.CustomerServiceView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mete Aydin
 * @date 23.10.2021
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
    public ResponseEntity<CustomerReadDTO> getById(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(customerServiceView.getById(customerId));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<CustomerReadDTO> delete(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(customerServiceView.delete(customerId));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<CustomerReadDTO>> search(@RequestBody() CustomerSearchCriteriaDTO filter,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(customerServiceView.search(filter, PageRequest.of(page, size)));
    }

    @GetMapping("/{customerId}/assign-company/{companyId}")
    public ResponseEntity<CustomerReadDTO> getById(@PathVariable("customerId") Long customerId,
                                                   @PathVariable("companyId") Long companyId) {
        return ResponseEntity.ok(customerServiceView.assignCompany(customerId, companyId));
    }
}
