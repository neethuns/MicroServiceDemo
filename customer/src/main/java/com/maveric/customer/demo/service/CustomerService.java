package com.maveric.customer.demo.service;


import com.maveric.customer.demo.dto.AccountDTO;
import com.maveric.customer.demo.dto.CustomerDTO;
import com.maveric.customer.demo.exception.CustomerNotActiveException;
import com.maveric.customer.demo.exception.CustomerNotFoundException;
import com.maveric.customer.demo.feign.AccountFeign;
import com.maveric.customer.demo.model.Account;
import com.maveric.customer.demo.model.Customer;
import com.maveric.customer.demo.model.CustomerAccountList;
import com.maveric.customer.demo.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static com.maveric.customer.demo.constants.Constants.Constant.CUSTOMERNOTACTIVE;
import static com.maveric.customer.demo.constants.Constants.Constant.CUSTOMERNOTFOUND;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    AccountFeign accountFeign;
    @Autowired
    RestTemplate restTemplate;

    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    public CustomerAccountList getById(String id) {
        Optional<Customer> customer = customerRepo.findById(id);
        CustomerAccountList customerAccountList = new CustomerAccountList();
        if (customer.isPresent()) {
            if (customer.get().getIsActive().equals(Boolean.TRUE)) {
                ResponseEntity<List<Account>> account = accountFeign.getAccountByCustomerId(id);
                customerAccountList.setCustomer(customer.get());
                customerAccountList.setAccount(account.getBody());
                return customerAccountList;
            } else {
                throw new CustomerNotActiveException(CUSTOMERNOTACTIVE);
            }
        } else {
            throw new CustomerNotFoundException(CUSTOMERNOTFOUND);
        }

    }

    public Customer getCustomerById(String id) {
        Optional<Customer> customer = customerRepo.findById(id);

        if (customer.isPresent()) {
            if (customer.get().getIsActive().equals(Boolean.TRUE)) {
                return customer.get();
            } else {
                throw new CustomerNotActiveException(CUSTOMERNOTACTIVE);
            }
        } else {
            throw new CustomerNotFoundException(CUSTOMERNOTFOUND);
        }
    }

    public Customer updateById(String id, CustomerDTO customerDTO) {
        Optional<Customer> selectedCustomer = customerRepo.findById(id);

        if (selectedCustomer.isPresent()) {
            Customer customerData = selectedCustomer.get();
            customerData.setPhoneNumber(customerDTO.getPhoneNumber());
            customerData.setAddress(customerDTO.getAddress());
            customerData.setEmail(customerDTO.getEmail());
            return customerRepo.save(customerData);

        } else
            throw new CustomerNotFoundException(CUSTOMERNOTFOUND);

    }


    public String deleteById(String id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            Customer customer1 = customer.get();
            customer1.setIsActive(accountFeign.deleteAccountByCustomerId(id));
            customerRepo.save(customer1);

        }

        return "Customer Deleted Successfully";
    }

}

