package io.github.genorchiomento.customerapi.application.service;

import io.github.genorchiomento.customerapi.application.dto.CustomerRequest;
import io.github.genorchiomento.customerapi.application.dto.CustomerResponse;
import io.github.genorchiomento.customerapi.application.exception.CustomerAlreadyExistsException;
import io.github.genorchiomento.customerapi.application.exception.CustomerNotFoundException;
import io.github.genorchiomento.customerapi.application.service.adapter.CustomerServiceAdapter;
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
        if (customerRepository.existsByCpf(request.cpf())) {
            throw new CustomerAlreadyExistsException("Customer already exists with CPF: " + request.cpf());
        }

        Customer customer = new Customer(
                null,
                request.cpf(),
                request.name(),
                request.birthDate(),
                request.phone(),
                request.address()
        );

        Customer savedCustomer = customerRepository.save(customer);
        return CustomerServiceAdapter.cast(savedCustomer);
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerServiceAdapter::cast)
                .collect(Collectors.toList());
    }

    public CustomerResponse getCustomerByCPF(String cpf) {
        Customer customer = customerRepository.findByCpf(cpf)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with CPF: " + cpf));
        return CustomerServiceAdapter.cast(customer);
    }

    public CustomerResponse updateCustomer(String cpf, CustomerRequest request) {
        Customer existingCustomer = customerRepository.findByCpf(cpf)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with CPF: " + cpf));

        Customer updatedCustomer = new Customer(
                existingCustomer.id(),
                existingCustomer.cpf(),
                request.name(),
                request.birthDate(),
                request.phone(),
                request.address()
        );

        updatedCustomer = customerRepository.save(updatedCustomer);

        return CustomerServiceAdapter.cast(updatedCustomer);
    }

    public void deleteCustomer(String cpf) {
        if (!customerRepository.existsByCpf(cpf)) {
            throw new CustomerNotFoundException("Customer not found with CPF: " + cpf);
        }
        customerRepository.delete(customerRepository.findByCpf(cpf).orElseThrow());
    }
}
