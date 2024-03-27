package com.lariss.bankproject.dto;

import com.lariss.bankproject.enumeration.MovementType;
import com.lariss.bankproject.model.Movement;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovementsViewDTO(
        Long movementId,
        BigDecimal amount,
        MovementType type,
        LocalDate movementDate
) {
    public MovementsViewDTO(Movement movement) {
        this(movement.getId(), movement.getAmount(), movement.getType(), movement.getMovementDate());
    }
}
