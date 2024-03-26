package com.lariss.bankproject.dto;

import com.lariss.bankproject.enumeration.AccountType;

public record AccountDTO(
        AccountType type,
        Long personId
) {
}

