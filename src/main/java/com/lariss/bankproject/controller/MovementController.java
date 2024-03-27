package com.lariss.bankproject.controller;

import com.lariss.bankproject.dto.MovementDTO;
import com.lariss.bankproject.dto.MovementsViewDTO;
import com.lariss.bankproject.dto.TransferMovementDTO;
import com.lariss.bankproject.model.Movement;
import com.lariss.bankproject.service.MovementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class MovementController {
    private final MovementService service;

    @PostMapping("/movements")
    public ResponseEntity<Void> registerMovement(@RequestBody MovementDTO movementDTO) {
        service.registerMovement(movementDTO);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/accounts/{accountId}/movements")
    @ResponseBody
    public List<MovementsViewDTO> getMovements(@PathVariable Long accountId,
                                               @RequestParam(required = false) LocalDate startDate,
                                               @RequestParam(required = false) LocalDate endDate) {
        List<Movement> movements = service.getMovements(accountId, startDate, endDate);
        return movements.stream()
                .map(MovementsViewDTO::new)
                .toList();
    }

    @PostMapping("/movements/transfer")
    public ResponseEntity<Void> transferMovement(@RequestBody TransferMovementDTO transferMovementDTO) {
        service.transferMovement(transferMovementDTO);
        return ResponseEntity.ok().build();
    }
}
