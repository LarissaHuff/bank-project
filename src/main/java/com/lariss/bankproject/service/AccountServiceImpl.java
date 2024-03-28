package com.lariss.bankproject.service;

import com.lariss.bankproject.dto.AccountDTO;
import com.lariss.bankproject.enumeration.AccountStatus;
import com.lariss.bankproject.enumeration.AccountType;
import com.lariss.bankproject.exception.BusinessException;
import com.lariss.bankproject.exception.NotFoundException;
import com.lariss.bankproject.model.Account;
import com.lariss.bankproject.model.Person;
import com.lariss.bankproject.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final PersonService personService;

    @Override
    public Long register(AccountDTO accountDTO) {
        Person person = personService.findById(accountDTO.personId());
        List<Account> accounts = person.getAccounts();

        verifyIfPersonHasAccountOfType(accounts, accountDTO.type());

        Account account = new Account();
        account.setType(accountDTO.type());
        account.setCreateDate(LocalDate.now());
        account.setBalance(BigDecimal.ZERO);
        account.setStatus(AccountStatus.ACTIVE);
        account.setPerson(person);

        return repository.save(account).getNumber();
    }

    @Override
    public List<Account> findByPersonIdAndType(Long personId, AccountType type) {
        return personService.findById(personId).getAccounts()
                .stream().filter(account -> type == null || account.getType() == type)
                .toList();
    }

    @Override
    public void updateStatus(Long number, AccountStatus status) {
        Account account = findById(number);
        account.setStatus(status);
        repository.save(account);
    }

    @Override
    public Account findById(Long number) {
        return repository.findById(number).orElseThrow(() -> new NotFoundException("Account"));
    }

    @Override
    public void save(Account account) {
        repository.save(account);
    }

    private void verifyIfPersonHasAccountOfType(List<Account> accounts, AccountType type) {
        boolean result = accounts.stream()
                .anyMatch(it -> it.getType() == type);
        if (result) {
            throw new BusinessException("Person already has an account of this type.");
        }
    }
}
