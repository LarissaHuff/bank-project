package com.lariss.bankproject.dto;

import java.math.BigDecimal;

public record TransferMovementDTO(
        Long destineAccountNumber,
        Long originAccountNumber,
        BigDecimal amount
) {
}
