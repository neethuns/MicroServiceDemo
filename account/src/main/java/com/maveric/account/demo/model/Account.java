package com.maveric.account.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import java.time.LocalDateTime;

@Entity
@Table(name="Account")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountId;
    private String customerId;
    private Boolean isCustomerActive;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "AccountType can not be null")
    private AccountTypes accountTypes;
    LocalDateTime dateTime;

    public Account(Boolean isCustomerActive, AccountTypes accountTypes, LocalDateTime dateTime) {
        this.isCustomerActive = isCustomerActive;
        this.accountTypes = accountTypes;
        this.dateTime = dateTime;
    }

    public Account(String customerId, Boolean isCustomerActive, AccountTypes accountTypes, LocalDateTime dateTime) {
        this.customerId = customerId;
        this.isCustomerActive = isCustomerActive;
        this.accountTypes = accountTypes;
        this.dateTime = dateTime;
    }
}
