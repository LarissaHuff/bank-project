package com.lariss.bankproject.dto;

import com.lariss.bankproject.enumeration.AccountType;
import com.lariss.bankproject.enumeration.Status;
import com.lariss.bankproject.model.Account;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AccountViewDTO(
        Long number,
        LocalDate createDate,
        BigDecimal balance,
        AccountType accountType,
        Status status) {
    public AccountViewDTO(Account account) {
        this(account.getNumber(), account.getCreateDate(), account.getBalance(), account.getType(), account.getStatus());

    }
}
