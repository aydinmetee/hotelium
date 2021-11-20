package com.hotelium.mainservice.endpoint.customer;

import com.hotelium.mainservice.dto.customer.CompanyReadDTO;
import com.hotelium.mainservice.dto.customer.CompanySearchCriteriaDTO;
import com.hotelium.mainservice.dto.customer.CompanyWriteDTO;
import com.hotelium.mainservice.serviceview.customer.CompanyServiceView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyServiceView companyServiceView;

    @PostMapping()
    public ResponseEntity<CompanyReadDTO> create(@RequestBody CompanyWriteDTO companyWriteDTO) {
        return ResponseEntity.ok(companyServiceView.create(companyWriteDTO));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyReadDTO> getById(@PathVariable("companyId") Long companyId) {
        return ResponseEntity.ok(companyServiceView.getById(companyId));
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<CompanyReadDTO> delete(@PathVariable("companyId") Long companyId) {
        return ResponseEntity.ok(companyServiceView.delete(companyId));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<CompanyReadDTO>> search(@RequestBody() CompanySearchCriteriaDTO filter,
                                                       Pageable pageable) {
        return ResponseEntity.ok(companyServiceView.search(filter, pageable));
    }
}
