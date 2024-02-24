package com.shaanku.banking.service;

import com.shaanku.banking.dto.AccountDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface AccountService {
    public ResponseEntity createAccount(AccountDTO accountDTO);

    ResponseEntity deleteAccount(UUID accountID);

    ResponseEntity getAccountDetails(String accountNumber);

    ResponseEntity depositMoney(String accountNumber, String depositAmount);

    ResponseEntity withdrawMoney(String accountNumber, String depositAmount);
}
