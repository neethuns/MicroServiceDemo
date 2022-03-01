package com.maveric.account.demo.repo;

import com.maveric.account.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account,Integer> {
    Optional<Account> findByAccountId(Integer accountId);

   List<Account> findByCustomerId(String customerId);

    List<Account>findAllByCustomerId(String customerId);
}
