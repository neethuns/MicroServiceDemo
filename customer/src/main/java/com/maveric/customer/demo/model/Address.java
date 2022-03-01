package com.maveric.customer.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
    @Data
    public class Address {
        private Integer addressId;
        private String street;
        private Integer pinCode;
    }


