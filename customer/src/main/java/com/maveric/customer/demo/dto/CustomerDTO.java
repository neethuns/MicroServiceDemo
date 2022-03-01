package com.maveric.customer.demo.dto;

import com.maveric.customer.demo.model.Address;
import com.maveric.customer.demo.model.CustomerTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    @Id
    private String id;
    @NotBlank(message="Name can not be blank")
    @Size(min=5,max=50)
    private String name;
    @Min(value=18,message = "Age should be greater than 18")
    @NotNull(message="Age can not be Null")
    private Integer age;
    @Length(min=10,max=15)
    private String phoneNumber;
    @NotBlank(message="Email can not be blank")
    @Email(message = "EmailId ")
    private String email;
    private Address address;
    private Boolean isActive;
    private CustomerTypes customerTypes;


}
