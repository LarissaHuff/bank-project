package com.lariss.bankproject.service;

import com.lariss.bankproject.dto.MovementDTO;
import com.lariss.bankproject.dto.TransferMovementDTO;
import com.lariss.bankproject.enumeration.MovementType;
import com.lariss.bankproject.exception.BusinessException;
import com.lariss.bankproject.model.Account;
import com.lariss.bankproject.model.Movement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class MovementServiceImpl implements MovementService {
    private final AccountService accountService;

    @Override
    public void registerMovement(MovementDTO movementDTO) {
        Account account = accountService.findById(movementDTO.accountId());
        updateAccountBalance(account, movementDTO.amount(), movementDTO.type());

        Movement movement = new Movement();
        movement.setType(movementDTO.type());
        movement.setAmount(movementDTO.amount());
        movement.setMovementDate(LocalDate.now());
        movement.setAccount(account);

        account.getMovements().add(movement);
        accountService.save(account);
    }


    @Override
    public List<Movement> getMovements(Long accountId, LocalDate startDate, LocalDate endDate) {
        Account account = accountService.findById(accountId);
        List<Movement> movementsList = account.getMovements();
        return movementsList.stream()
                .filter(it -> filterByDate(it.getMovementDate(), startDate, endDate))
                .toList();
    }

    @Override
    public void transferMovement(TransferMovementDTO transferMovementDTO) {
        MovementDTO movementWithdraw = new MovementDTO(transferMovementDTO.originAccountNumber(),
                transferMovementDTO.amount(), MovementType.WITHDRAW);

        registerMovement(movementWithdraw);

        MovementDTO movementDeposit = new MovementDTO(transferMovementDTO.destineAccountNumber(),
                transferMovementDTO.amount(), MovementType.DEPOSIT);

        registerMovement(movementDeposit);
    }

    private boolean filterByDate(LocalDate movementDate, LocalDate startDate, LocalDate endDate) {
        return (endDate == null || movementDate.isBefore(endDate) || movementDate.equals(endDate))
                && (startDate == null || movementDate.isAfter(startDate) || movementDate.equals(startDate));
    }

    private void updateAccountBalance(Account account, BigDecimal amount, MovementType type) {
        BigDecimal currentBalance = account.getBalance();
        BigDecimal newBalance = getNewBalance(amount, type, currentBalance);

        account.setBalance(newBalance);
    }

    private static BigDecimal getNewBalance(BigDecimal amount, MovementType type, BigDecimal currentBalance) {
        BigDecimal newBalance;
        if (type == MovementType.DEPOSIT) {
            newBalance = currentBalance.add(amount);
        } else {
            if (currentBalance.compareTo(amount) < 0) {
                throw new BusinessException("Not enough balance.");
            }
            newBalance = currentBalance.subtract(amount);
        }
        return newBalance;
    }
}
