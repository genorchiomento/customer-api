package io.github.genorchiomento.customerapi.application.service.adapter;

import io.github.genorchiomento.customerapi.application.dto.CustomerResponse;
import io.github.genorchiomento.customerapi.domain.model.Customer;

public class CustomerServiceAdapter {

    public static CustomerResponse cast(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setCpf(customer.getCpf());
        response.setName(customer.getName());
        response.setBirthDate(customer.getBirthDate());
        response.setPhone(customer.getPhone());
        response.setAddress(customer.getAddress());
        return response;
    }
}