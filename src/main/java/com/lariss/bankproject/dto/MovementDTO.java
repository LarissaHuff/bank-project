package com.lariss.bankproject.dto;

import com.lariss.bankproject.enumeration.MovementType;

import java.math.BigDecimal;

public record MovementDTO(
        Long accountId,
        BigDecimal amount,
        MovementType type
) {

}
