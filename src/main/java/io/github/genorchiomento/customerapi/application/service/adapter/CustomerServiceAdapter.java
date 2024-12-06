package io.github.genorchiomento.customerapi.application.service.adapter;

import io.github.genorchiomento.customerapi.application.dto.CustomerResponse;
import io.github.genorchiomento.customerapi.domain.model.Customer;

public class CustomerServiceAdapter {

    public static CustomerResponse cast(Customer customer) {
        return new CustomerResponse(
                customer.id(),
                customer.cpf(),
                customer.name(),
                customer.birthDate(),
                customer.phone(),
                customer.address()
        );
    }
}