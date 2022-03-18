package com.hotelium.mainservice.endpoint.customer;

import com.hotelium.mainservice.dto.customer.CompanyReadDTO;
import com.hotelium.mainservice.dto.customer.CompanySearchCriteriaDTO;
import com.hotelium.mainservice.dto.customer.CompanyWriteDTO;
import com.hotelium.mainservice.serviceview.customer.CompanyServiceView;
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
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyServiceView companyServiceView;

    @PostMapping()
    public ResponseEntity<CompanyReadDTO> create(@RequestBody CompanyWriteDTO companyWriteDTO) {
        return ResponseEntity.ok(companyServiceView.create(companyWriteDTO));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyReadDTO> getById(@PathVariable("companyId") String companyId) {
        return ResponseEntity.ok(companyServiceView.getById(companyId));
    }

    @PutMapping("/{companyId}")
    @ApiOperation(value = "Update Company", response = CompanyReadDTO.class)
    public ResponseEntity<CompanyReadDTO> update(@PathVariable("companyId") String companyId,
                                                 @RequestBody CompanyWriteDTO companyWriteDTO) {
        return ResponseEntity.ok(companyServiceView.update(companyId, companyWriteDTO));
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<CompanyReadDTO> delete(@PathVariable("companyId") String companyId) {
        return ResponseEntity.ok(companyServiceView.delete(companyId));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<CompanyReadDTO>> search(@RequestBody() CompanySearchCriteriaDTO filter,
                                                       Pageable pageable) {
        return ResponseEntity.ok(companyServiceView.search(filter, pageable));
    }
}
