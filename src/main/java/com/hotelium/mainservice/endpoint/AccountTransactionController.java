package com.hotelium.mainservice.endpoint;

import com.hotelium.mainservice.dto.AccountTransactionBalanceDTO;
import com.hotelium.mainservice.dto.AccountTransactionReadDTO;
import com.hotelium.mainservice.dto.AccountTransactionSearchCriteriaDTO;
import com.hotelium.mainservice.dto.AccountTransactionWriteDTO;
import com.hotelium.mainservice.serviceview.AccountTransactionServiceView;
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

import java.text.ParseException;

/**
 * @author Mete Aydin
 * @date 23.10.2021
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
}
