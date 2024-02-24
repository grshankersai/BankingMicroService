package com.shaanku.banking.service.impl;

import com.shaanku.banking.dao.AccountDao;
import com.shaanku.banking.entity.Account;
import com.shaanku.banking.exception.AccountNotFoundException;
import com.shaanku.banking.exception.InsufficientBalanceException;
import com.shaanku.banking.dto.AccountDTO;
import com.shaanku.banking.mapper.AccountMapper;
import com.shaanku.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Autowired
    AccountMapper accountMapper;

    @Override
    public ResponseEntity createAccount(AccountDTO accountDTO) {
        UUID accountId = UUID.randomUUID();
        Account account = new Account(accountId, accountDTO.getName(), accountDTO.getBalance());
        accountDao.save(account);
        return new ResponseEntity<>("Account Created", HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteAccount(UUID accountID) {
        accountDao.delete(accountDao.getByAccountNumber(accountID).orElseThrow(() -> new AccountNotFoundException("Account not found")));
        return new ResponseEntity("Account Deleted", HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAccountDetails(String accountNumber) {
        Account account = accountDao.getByAccountNumber(UUID.fromString(accountNumber)).orElseThrow(() -> new AccountNotFoundException("Account Doesnt Exist"));
        AccountDTO accountDTO = accountMapper.convertToDTO(account);
        return new ResponseEntity(accountDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity depositMoney(String accountNumber, String depositAmount) {
        UUID accountNumberOfUser = UUID.fromString(accountNumber);
        Account account = accountDao.getByAccountNumber(accountNumberOfUser).orElseThrow(() -> new AccountNotFoundException("Account Doesn't Exist"));
        Integer amountToDeposit = Integer.parseInt(depositAmount);
        account.setAccountBalance(account.getAccountBalance() + amountToDeposit);
        accountDao.save(account);
        return new ResponseEntity("Balance Updated Successfully New Balance is " + account.getAccountBalance(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity withdrawMoney(String accountNumber, String withdrawAmount) {
        UUID accountNumberOfUser = UUID.fromString(accountNumber);
        Account account = accountDao.getByAccountNumber(accountNumberOfUser).orElseThrow(() -> new AccountNotFoundException("Account Doesn't Exist"));
        if (account.getAccountBalance() > Integer.parseInt(withdrawAmount)) {
            account.setAccountBalance(account.getAccountBalance() - Integer.parseInt(withdrawAmount));
            accountDao.save(account);
            return new ResponseEntity("Amount Withdrawn Successfully Available balance" + account.getAccountBalance(), HttpStatus.OK);
        } else {
            throw new InsufficientBalanceException("Cannot Withdraw because your current balance is " + account.getAccountBalance());
        }
    }
}
