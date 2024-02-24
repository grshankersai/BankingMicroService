package com.shaanku.banking.dao;

import com.shaanku.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountDao extends JpaRepository<Account, UUID> {
    Optional<Account> getByAccountNumber(UUID accountNumber);
}
