package com.lariss.bankproject.service;

import com.lariss.bankproject.dto.MovementDTO;
import com.lariss.bankproject.dto.TransferMovementDTO;
import com.lariss.bankproject.model.Movement;

import java.time.LocalDate;
import java.util.List;

public interface MovementService {
    void registerMovement(MovementDTO movementDTO);
    List<Movement> getMovements(Long accountId, LocalDate startDate, LocalDate endDate);

    void transferMovement(TransferMovementDTO transferMovementDTO);
}
