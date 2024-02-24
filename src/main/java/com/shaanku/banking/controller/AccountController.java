package com.shaanku.banking.controller;

import com.shaanku.banking.dto.AccountDTO;
import com.shaanku.banking.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api")
@Slf4j
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("get-details/{accountNumber}")
    public ResponseEntity getAccountDetails(@PathVariable String accountNumber) {
        return accountService.getAccountDetails(accountNumber);
    }

    @PostMapping("create-account")
    public ResponseEntity createAccount(@RequestBody AccountDTO accountDTO) {
        return accountService.createAccount(accountDTO);
    }

    @PutMapping("deposit")
    public ResponseEntity depositMoney(@RequestBody Map<String, Object> requestBody) {
        return accountService.depositMoney(requestBody.get("accountNumber").toString(), requestBody.get("depositAmount").toString());
    }

    @PutMapping("withdraw")
    public ResponseEntity withdrawMoney(@RequestBody Map<String, Object> requestBody) {
        return accountService.withdrawMoney(requestBody.get("accountNumber").toString(), requestBody.get("withdrawAmount").toString());
    }

    @DeleteMapping("delete-account")
    public ResponseEntity deleteAccount(@RequestBody String accountId) {
        return accountService.deleteAccount(UUID.fromString(accountId.trim()));
    }


}
