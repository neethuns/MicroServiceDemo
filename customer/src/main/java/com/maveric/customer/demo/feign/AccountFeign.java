package com.maveric.customer.demo.feign;

import com.maveric.customer.demo.dto.AccountDTO;
import com.maveric.customer.demo.model.Account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Component
@FeignClient(name = "account", fallbackFactory = HystrixFallBackFactory.class)
public interface AccountFeign {

    @GetMapping("/accountService/accounts/customerId/{customerId}")
    ResponseEntity<List<Account>> getAccountByCustomerId(@PathVariable("id") String id);

    @PostMapping("/accountService/accounts")
    Boolean accountCreation(@RequestBody AccountDTO accountDTO);

    @DeleteMapping("/accountService/accounts/{customerId}")
    Boolean deleteAccountByCustomerId(@PathVariable("customerId") String id);
   }
