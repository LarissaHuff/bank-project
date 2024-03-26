package com.lariss.bankproject.service;

import com.lariss.bankproject.dto.AccountDTO;
import com.lariss.bankproject.enumeration.AccountType;
import com.lariss.bankproject.enumeration.Status;
import com.lariss.bankproject.model.Account;

import java.util.List;

public interface AccountService {
    Long register(AccountDTO accountDTO);

    List<Account> findByPersonIdAndType(Long personId, AccountType type);

    void updateStatus(Long number, Status status);

    Account findById(Long number);
}
