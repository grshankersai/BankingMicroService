package com.shaanku.banking.mapper;

import com.shaanku.banking.entity.Account;
import com.shaanku.banking.dto.AccountDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Account convertToEntity(AccountDTO accountDTO) {
        return modelMapper.map(accountDTO, Account.class);
    }

    public AccountDTO convertToDTO(Account account) {
        return modelMapper.map(account, AccountDTO.class);
    }


}
