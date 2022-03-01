package com.maveric.account.demo.service;

import com.maveric.account.demo.exception.AccountNotFoundException;
import com.maveric.account.demo.exception.CustomerNotActiveException;
import com.maveric.account.demo.exception.CustomerNotFoundException;
import com.maveric.account.demo.model.Account;
import com.maveric.account.demo.model.AccountTypes;
import com.maveric.account.demo.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.maveric.account.demo.constants.Constants.Constant.*;


@Service
public class AccountService {
    List<Account> accountList = new ArrayList<>();
    @Autowired
    AccountRepo accountRepo;

    public Account createAccount(Account account) {
        return accountRepo.save(account);
    }

    public List<Account> getAccounts() {
        accountList = accountRepo.findAll();
        return accountList;
    }

    public Optional<Account> getAccountById(Integer accountId) {
        return accountRepo.findByAccountId(accountId);

    }

    public Account createAccountNext(Account account) {

        return accountRepo.save(account);
    }


    public List<Account> getAccountsByCustomerId(String customerId) {
        List<Account> accounts = accountRepo.findByCustomerId(customerId);
        if (accounts.isEmpty())
            throw new CustomerNotFoundException(CUSTOMERNOTFOUND);
        else
            return accounts;

    }

    public Boolean getAccountByCustomerId(String customerId) {
        List<Account> accounts = accountRepo.findByCustomerId(customerId);
        //below if checks whether the id passed is valid
        if (accounts.isEmpty()) {
            throw new CustomerNotFoundException(CUSTOMERNOTFOUND);
        }
        for (Account account : accounts) {
            //below if checks whether any account is active for this customer
            if (account.getIsCustomerActive().equals(Boolean.FALSE))
                throw new CustomerNotActiveException(CUSTOMERNOTACTIVE);

        }
        return true;
    }

    public Boolean isActive(String customerId) {

        List<Account> accounts = new ArrayList<>(accountRepo.findByCustomerId(customerId));

        if (accounts.isEmpty()) {
            return false;
        }
        for (Account account : accounts) {

            if (Boolean.FALSE.equals(account.getIsCustomerActive()))
                throw new CustomerNotActiveException(CUSTOMERNOTACTIVE);
        }
        return true;
    }

    public Boolean isAccountTypeAlreadyExist(String customerId, AccountTypes accountTypes) {

        List<Account> accountTypeList = new ArrayList<>(accountRepo.findByCustomerId(customerId));
        for (Account account : accountTypeList) {
            if (account.getAccountTypes() == accountTypes)
                return true;
        }
        return false;


    }

    public Boolean deleteAccountByCustomer(String customerId) {
        List<Account> accounts = accountRepo.findByCustomerId(customerId);
        if (!accountList.isEmpty()) {
            for (Account account : accounts) {
                account.setIsCustomerActive(Boolean.FALSE);
                accountRepo.save(account);
            }
            return false;
        } else {
            throw new AccountNotFoundException(ACCOUNTNOTFOUND);
        }
    }
}