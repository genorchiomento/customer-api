package io.github.genorchiomento.customerapi.application.service;

import io.github.genorchiomento.customerapi.application.dto.CustomerRequest;
import io.github.genorchiomento.customerapi.application.dto.CustomerResponse;
import io.github.genorchiomento.customerapi.domain.model.Customer;
import io.github.genorchiomento.customerapi.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setCpf(request.getCpf());
        customer.setName(request.getName());
        customer.setBirthDate(request.getBirthDate());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());

        Customer savedCustomer = customerRepository.save(customer);
        return mapToResponse(savedCustomer);
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private CustomerResponse mapToResponse(Customer customer) {
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