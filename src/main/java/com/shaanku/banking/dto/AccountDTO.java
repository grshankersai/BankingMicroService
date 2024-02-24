package com.shaanku.banking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AccountDTO {
    String name;
    Integer balance;

    public AccountDTO(String name, Integer balance) {
        this.name = name;
        this.balance = balance;
    }
}
