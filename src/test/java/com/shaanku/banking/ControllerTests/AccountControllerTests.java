package com.shaanku.banking.ControllerTests;

import com.shaanku.banking.controller.AccountController;
import com.shaanku.banking.dto.AccountDTO;
import com.shaanku.banking.entity.Account;
import com.shaanku.banking.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AccountControllerTests {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName("Shanker Sai");
        accountDTO.setBalance(1000);

        when(accountService.createAccount(accountDTO)).thenReturn(new ResponseEntity<>("Account Created", HttpStatus.OK));

        ResponseEntity responseEntity = accountController.createAccount(accountDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Account Created", responseEntity.getBody());
        verify(accountService, atLeastOnce()).createAccount(accountDTO);

    }

    @Test
    void testGetAccountDetails() {
        String accountNumber = "fa4831e3-3daa-4935-b112-08ef9e88d794";
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName("Shanker Sai");
        accountDTO.setBalance(1000);
        when(accountService.getAccountDetails(accountNumber)).thenReturn(new ResponseEntity(accountDTO, HttpStatus.OK));

        ResponseEntity responseEntity = accountController.getAccountDetails(accountNumber);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(accountDTO, responseEntity.getBody());
        verify(accountService, atLeastOnce()).getAccountDetails(accountNumber);

    }

    @Test
    void testDeleteAccount() {
        String accountNumber = "fa4831e3-3daa-4935-b112-08ef9e88d794";
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName("Shanker Sai");
        accountDTO.setBalance(1000);
        when(accountService.deleteAccount(UUID.fromString(accountNumber))).thenReturn(new ResponseEntity(accountDTO, HttpStatus.OK));

        ResponseEntity responseEntity = accountController.deleteAccount("fa4831e3-3daa-4935-b112-08ef9e88d794");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(accountDTO, responseEntity.getBody());
        verify(accountService, times(1)).deleteAccount(UUID.fromString(accountNumber));
    }

    @Test
    void testDepositMoney() {
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("accountNumber", "b60afd44-0650-42ee-a60b-030178b5be7f");
        requestBody.put("depositAmount", "2000");


        when(accountService.depositMoney(requestBody.get("accountNumber").toString(), requestBody.get("depositAmount").toString())).thenReturn(new ResponseEntity("Balance Updated Successfully New Balance is 2000", HttpStatus.OK));

        ResponseEntity responseEntity = accountController.depositMoney(requestBody);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Balance Updated Successfully New Balance is 2000", responseEntity.getBody());
        verify(accountService, atLeastOnce()).depositMoney(requestBody.get("accountNumber").toString(), requestBody.get("depositAmount").toString());
    }

    @Test
    void testWithdraw() {
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("accountNumber", "b60afd44-0650-42ee-a60b-030178b5be7f");
        requestBody.put("withdrawAmount", "2000");

        when(accountService.withdrawMoney(requestBody.get("accountNumber").toString(), requestBody.get("withdrawAmount").toString())).thenReturn(new ResponseEntity("Amount Withdrawn Successfully Available balance 2000", HttpStatus.OK));

        ResponseEntity responseEntity = accountController.withdrawMoney(requestBody);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Amount Withdrawn Successfully Available balance 2000", responseEntity.getBody());
        verify(accountService, times(1)).withdrawMoney(requestBody.get("accountNumber").toString(), requestBody.get("withdrawAmount").toString());
    }
}
