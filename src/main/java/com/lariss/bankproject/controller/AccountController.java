package com.lariss.bankproject.controller;

import com.lariss.bankproject.dto.AccountDTO;
import com.lariss.bankproject.dto.AccountViewDTO;
import com.lariss.bankproject.enumeration.AccountStatus;
import com.lariss.bankproject.enumeration.AccountStatusDetail;
import com.lariss.bankproject.enumeration.AccountType;
import com.lariss.bankproject.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody AccountDTO accountDTO) {
        Long createdId = service.register(accountDTO);
        return ResponseEntity.created(URI.create("/accounts/" + createdId)).build();
    }

    @GetMapping("/persons/{personId}")
    @ResponseBody
    public List<AccountViewDTO> findByPersonIdAndType(@PathVariable Long personId,
                                                @RequestParam(required = false) AccountType type) {
        return service.findByPersonIdAndType(personId, type).stream()
                .map(AccountViewDTO::new)
                .toList();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{number}")
    public void updateStatus(@PathVariable Long number, @RequestParam AccountStatus status,
                             @RequestParam(required = false) AccountStatusDetail statusDetail) {
        service.updateStatus(number, status, statusDetail);
    }

    @GetMapping("/{number}")
    @ResponseBody
    public AccountViewDTO findById(@PathVariable Long number) {
        return new AccountViewDTO(service.findById(number));
    }

}
