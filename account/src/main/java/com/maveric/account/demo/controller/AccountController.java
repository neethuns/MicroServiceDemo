package com.maveric.account.demo.controller;

import com.maveric.account.demo.dto.AccountDTO;
import com.maveric.account.demo.exception.AccountNotFoundException;
import com.maveric.account.demo.exception.AccountTypeAlreadyExistsException;
import com.maveric.account.demo.exception.CustomerNotFoundException;
import com.maveric.account.demo.model.Account;
import com.maveric.account.demo.model.AccountTypes;
import com.maveric.account.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.maveric.account.demo.constants.Constants.Constant.*;

@RestController

@RequestMapping("/accountService")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/accounts")
    public Boolean accountCreation(@RequestBody AccountDTO accountDTO) {
        accountDTO.setDateTime(LocalDateTime.now());
        accountDTO.setIsCustomerActive(Boolean.TRUE);
        accountDTO.setAccountTypes(AccountTypes.CASH);
        return accountService.createAccount(new Account(accountDTO.getCustomerId(), accountDTO.getIsCustomerActive(), accountDTO.getAccountTypes(), accountDTO.getDateTime())).getIsCustomerActive();

    }

    @DeleteMapping("/accounts/{customerId}")
    Boolean deleteAccountByCustomerId(@PathVariable String customerId) {
        return accountService.deleteAccountByCustomer(customerId);

    }

    @PostMapping("/accounts/{customerId}")
    public ResponseEntity<Account> createAccountNext(@Valid @RequestBody AccountDTO accountDTO, @PathVariable String customerId) {

        if (Boolean.FALSE.equals(accountService.isActive(customerId))) {
            throw new CustomerNotFoundException(CUSTOMERNOTFOUND);
        } else {
            if (Boolean.TRUE.equals(accountService.isAccountTypeAlreadyExist(customerId, accountDTO.getAccountTypes()))) {
                throw new AccountTypeAlreadyExistsException(ACCOUNTTYPEEXISTS);
            } else {
                accountDTO.setCustomerId(customerId);
                accountDTO.setDateTime(LocalDateTime.now());
                accountDTO.setIsCustomerActive(Boolean.TRUE);
                return new ResponseEntity<>(accountService.createAccountNext(new Account(accountDTO.getCustomerId(), accountDTO.getIsCustomerActive(), accountDTO.getAccountTypes(), accountDTO.getDateTime())), HttpStatus.CREATED);

            }
        }
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAccount() {

        List<Account> accountList = accountService.getAccounts();
        return new ResponseEntity<>(accountList, HttpStatus.CREATED);


    }

    @GetMapping("/accounts/id/{accountId}")
    public ResponseEntity<Account> getAccountByAccountId(@PathVariable Integer accountId) {
        Optional<Account> account = accountService.getAccountById(accountId);
        if (!account.isPresent()) {
            throw new AccountNotFoundException(ACCOUNTNOTFOUND);
        } else {
            return new ResponseEntity<>(account.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/accounts/customerId/{customerId}")
    public ResponseEntity<List<Account>> getAccountByCustomerId(@PathVariable String customerId) {
        List<Account> account = accountService.getAccountsByCustomerId(customerId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
