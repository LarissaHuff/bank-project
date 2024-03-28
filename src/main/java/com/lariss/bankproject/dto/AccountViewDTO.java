package com.lariss.bankproject.dto;

import com.lariss.bankproject.enumeration.AccountStatus;
import com.lariss.bankproject.enumeration.AccountType;
import com.lariss.bankproject.model.Account;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AccountViewDTO(
        Long number,
        LocalDate createDate,
        BigDecimal balance,
        AccountType accountType,
        AccountStatus status) {
    public AccountViewDTO(Account account) {
        this(account.getNumber(), account.getCreateDate(), account.getBalance(), account.getType(), account.getStatus());

    }
}
