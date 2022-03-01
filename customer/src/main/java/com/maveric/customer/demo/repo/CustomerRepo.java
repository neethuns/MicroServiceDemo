package com.maveric.customer.demo.repo;

import com.maveric.customer.demo.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends MongoRepository<Customer,String> {


}
