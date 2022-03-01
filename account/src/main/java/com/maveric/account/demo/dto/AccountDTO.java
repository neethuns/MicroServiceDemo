package com.maveric.account.demo.dto;

import com.maveric.account.demo.model.AccountTypes;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
public class AccountDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountId;
    private String customerId;
    private Boolean isCustomerActive;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "AccountType can not be null")
    private AccountTypes accountTypes;


    LocalDateTime dateTime;
}
