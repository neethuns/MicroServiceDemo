package com.maveric.customer.demo.controller;

import com.maveric.customer.demo.dto.AccountDTO;
import com.maveric.customer.demo.dto.CustomerDTO;
import com.maveric.customer.demo.service.CustomerService;
import com.maveric.customer.demo.feign.AccountFeign;
import com.maveric.customer.demo.model.Customer;
import com.maveric.customer.demo.model.CustomerAccountList;
import com.maveric.customer.demo.repo.CustomerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/customerService")
public class CustomerController {

    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AccountFeign accountFeign;

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {

        AccountDTO accountDTO = new AccountDTO();
        Customer customerCreated = customerService.createCustomer(new Customer(customerDTO.getName(), customerDTO.getAge(), customerDTO.getPhoneNumber(), customerDTO.getEmail(), customerDTO.getAddress(), customerDTO.getCustomerTypes()));
        accountDTO.setCustomerId(customerCreated.getId());
        customerCreated.setIsActive(accountFeign.accountCreation(accountDTO));

        return new ResponseEntity<>(customerService.createCustomer(customerCreated), HttpStatus.CREATED);


    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerAccountList> getAccountByCustomerId(@PathVariable("id") String id) {

        return new ResponseEntity<>(customerService.getById(id), HttpStatus.OK);


    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomer() {
        try {
            logger.info("Enter into controller");
            List<Customer> customerList = customerService.getCustomers();
            return new ResponseEntity<>(customerList, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers/id/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id) {
        Customer customer = customerService.getCustomerById(id);

        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
        logger.info("Enter into update customer ");
        logger.info("Customer details {}", customerDTO);
        return new ResponseEntity<>(customerService.updateById(id, customerDTO), HttpStatus.OK);

    }


    @DeleteMapping("/customer/{id}")
    public String deleteCustomer(@PathVariable String id) {

        return customerService.deleteById(id);

    }
}