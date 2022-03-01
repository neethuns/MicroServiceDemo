package com.maveric.customer.demo.dto;

import com.maveric.customer.demo.model.AccountTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDTO {

    private Integer accountId;
    private String customerId;
    private Boolean isCustomerActive;
    private AccountTypes accountTypes;

    LocalDateTime dateTime;
}

