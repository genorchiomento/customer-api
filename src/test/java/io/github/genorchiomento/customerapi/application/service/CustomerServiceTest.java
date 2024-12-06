package io.github.genorchiomento.customerapi.application.service;

import io.github.genorchiomento.customerapi.application.dto.CustomerRequest;
import io.github.genorchiomento.customerapi.application.dto.CustomerResponse;
import io.github.genorchiomento.customerapi.application.exception.CustomerAlreadyExistsException;
import io.github.genorchiomento.customerapi.application.exception.CustomerNotFoundException;
import io.github.genorchiomento.customerapi.domain.model.Customer;
import io.github.genorchiomento.customerapi.domain.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCustomer_shouldSaveAndReturnCustomerResponse() {
        CustomerRequest request = new CustomerRequest(
                "12345678900",
                "João Silva",
                "1990-05-15",
                "9999999999",
                "Rua das Flores, 123"
        );

        Customer customer = new Customer(
                null,
                "12345678900",
                "João Silva",
                "1990-05-15",
                "9999999999",
                "Rua das Flores, 123"
        );

        when(customerRepository.existsByCpf(request.cpf())).thenReturn(false);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerResponse response = customerService.createCustomer(request);

        assertNotNull(response);
        assertEquals("12345678900", response.cpf());
        assertEquals("João Silva", response.name());
        verify(customerRepository, times(1)).existsByCpf(request.cpf());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void createCustomer_shouldThrowExceptionWhenCustomerAlreadyExists() {
        CustomerRequest request = new CustomerRequest(
                "12345678900",
                "João Silva",
                "1990-05-15",
                "9999999999",
                "Rua das Flores, 123"
        );

        when(customerRepository.existsByCpf(request.cpf())).thenReturn(true);

        Exception exception =
                assertThrows(CustomerAlreadyExistsException.class, () -> customerService.createCustomer(request));
        assertEquals("Customer already exists with CPF: 12345678900", exception.getMessage());
        verify(customerRepository, times(1)).existsByCpf(request.cpf());
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    void getCustomerByCPF_shouldReturnCustomerResponseWhenFound() {
        Customer customer = new Customer(
                "1",
                "12345678900",
                "João Silva",
                "1990-05-15",
                "9999999999",
                "Rua das Flores, 123"
        );

        when(customerRepository.findByCpf("12345678900")).thenReturn(Optional.of(customer));

        CustomerResponse response = customerService.getCustomerByCPF("12345678900");

        assertNotNull(response);
        assertEquals("12345678900", response.cpf());
        verify(customerRepository, times(1)).findByCpf("12345678900");
    }

    @Test
    void getCustomerByCPF_shouldThrowExceptionWhenNotFound() {
        when(customerRepository.findByCpf("12345678900")).thenReturn(Optional.empty());

        Exception exception =
                assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerByCPF("12345678900"));
        assertEquals("Customer not found with CPF: 12345678900", exception.getMessage());
        verify(customerRepository, times(1)).findByCpf("12345678900");
    }

    @Test
    void updateCustomer_shouldUpdateAndReturnCustomerResponse() {
        CustomerRequest request = new CustomerRequest("12345678900",
                "João Silva Atualizado",
                "1990-05-15",
                "9999999999",
                "Rua das Orquídeas, 123"
        );

        Customer existingCustomer = new Customer("1",
                "12345678900",
                "João Silva",
                "1990-05-15",
                "9999999999",
                "Rua das Flores, 123"
        );

        Customer updatedCustomer = new Customer("1",
                "12345678900",
                "João Silva Atualizado",
                "1990-05-15",
                "9999999999",
                "Rua das Orquídeas, 123");

        when(customerRepository.findByCpf("12345678900")).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);

        CustomerResponse response = customerService.updateCustomer("12345678900", request);

        assertNotNull(response);
        assertEquals("João Silva Atualizado", response.name());
        verify(customerRepository, times(1)).findByCpf("12345678900");
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void updateCustomer_shouldThrowExceptionWhenNotFound() {
        CustomerRequest request = new CustomerRequest(
                "12345678900",
                "João Silva Atualizado",
                "1990-05-15",
                "9999999999",
                "Rua das Orquídeas, 123"
        );

        when(customerRepository.findByCpf("12345678900")).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomerNotFoundException.class, () -> customerService
                .updateCustomer("12345678900", request));
        assertEquals("Customer not found with CPF: 12345678900", exception.getMessage());
        verify(customerRepository, times(1)).findByCpf("12345678900");
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    void deleteCustomer_shouldDeleteCustomerWhenFound() {
        Customer customer = new Customer("1",
                "12345678900",
                "João Silva",
                "1990-05-15",
                "9999999999",
                "Rua das Flores, 123"
        );

        when(customerRepository.existsByCpf("12345678900")).thenReturn(true);
        when(customerRepository.findByCpf("12345678900")).thenReturn(Optional.of(customer));

        customerService.deleteCustomer("12345678900");

        verify(customerRepository, times(1)).existsByCpf("12345678900");
        verify(customerRepository, times(1)).findByCpf("12345678900");
        verify(customerRepository, times(1)).delete(customer);
    }

    @Test
    void deleteCustomer_shouldThrowExceptionWhenNotFound() {
        when(customerRepository.existsByCpf("12345678900")).thenReturn(false);

        Exception exception = assertThrows(CustomerNotFoundException.class, () -> customerService
                .deleteCustomer("12345678900"));
        assertEquals("Customer not found with CPF: 12345678900", exception.getMessage());
        verify(customerRepository, times(1)).existsByCpf("12345678900");
        verify(customerRepository, never()).delete(any(Customer.class));
    }

    @Test
    void getAllCustomers_shouldReturnListOfCustomerResponses() {
        List<Customer> customers = List.of(
                new Customer("1",
                        "12345678900",
                        "João Silva",
                        "1990-05-15",
                        "9999999999",
                        "Rua das Flores, 123"),
                new Customer("2",
                        "98765432100",
                        "Maria Silva",
                        "1992-10-10",
                        "8888888888",
                        "Rua das Palmeiras, 456")
        );
        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerResponse> responses = customerService.getAllCustomers();

        assertNotNull(responses);
        assertEquals(2, responses.size());
        assertEquals("12345678900", responses.get(0).cpf());
        assertEquals("98765432100", responses.get(1).cpf());
        verify(customerRepository, times(1)).findAll();
    }
}
