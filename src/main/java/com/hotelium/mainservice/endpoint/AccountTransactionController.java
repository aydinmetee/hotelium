package com.hotelium.mainservice.endpoint;

import com.hotelium.mainservice.dto.*;
import com.hotelium.mainservice.serviceview.AccountTransactionServiceView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@RestController
@RequestMapping("/acc")
@RequiredArgsConstructor
public class AccountTransactionController {
    private final AccountTransactionServiceView accountTransactionServiceView;

    @PostMapping("/expense")
    public ResponseEntity<AccountTransactionReadDTO> createExpense(@RequestBody AccountTransactionWriteDTO accountTransactionWriteDTO) {
        return ResponseEntity.ok(accountTransactionServiceView.createExpense(accountTransactionWriteDTO));
    }

    @GetMapping("/get-montly-balance")
    public ResponseEntity<AccountTransactionBalanceDTO> getBalanceMontly() throws ParseException {
        return ResponseEntity.ok(accountTransactionServiceView.getMontly());
    }


    @DeleteMapping("/{accountId}")
    public ResponseEntity<AccountTransactionReadDTO> delete(@PathVariable("accountId") Long accountId) {
        return ResponseEntity.ok(accountTransactionServiceView.delete(accountId));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<AccountTransactionReadDTO>> search(@RequestBody() AccountTransactionSearchCriteriaDTO filter,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(accountTransactionServiceView.search(filter, PageRequest.of(page, size)));
    }

    @PutMapping("/update-source")
    public ResponseEntity<AccountTransactionReadDTO> updateSource(@RequestBody AccountTransactionSourceUpdateDTO updateDTO) {
        return ResponseEntity.ok(accountTransactionServiceView.updateSource(updateDTO));
    }
}
