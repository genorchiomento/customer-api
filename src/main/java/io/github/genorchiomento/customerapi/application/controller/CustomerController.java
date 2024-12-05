package io.github.genorchiomento.customerapi.application.controller;

import io.github.genorchiomento.customerapi.application.dto.CustomerRequest;
import io.github.genorchiomento.customerapi.application.dto.CustomerResponse;
import io.github.genorchiomento.customerapi.application.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer", description = "Customer management APIs")
@Validated
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @Operation(summary = "Create a new customer", description = "Creates a new customer with the provided information")
    @ApiResponse(responseCode = "201", description = "Customer created successfully")
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Get all customers", description = "Retrieves a list of all customers")
    @ApiResponse(responseCode = "200", description = "List of customers retrieved successfully")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> response = customerService.getAllCustomers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cpf}")
    @Operation(summary = "Get a customer by CPF", description = "Retrieves a customer's information based on their CPF")
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    public ResponseEntity<CustomerResponse> getCustomerByCPF(@PathVariable String cpf) {
        CustomerResponse response = customerService.getCustomerByCPF(cpf);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{cpf}")
    @Operation(summary = "Update a customer", description = "Updates an existing customer's information")
    @ApiResponse(responseCode = "200", description = "Customer updated successfully")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable String cpf,
            @Valid @RequestBody CustomerRequest request
    ) {
        CustomerResponse response = customerService.updateCustomer(cpf, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{cpf}")
    @Operation(summary = "Delete a customer", description = "Deletes a customer based on their CPF")
    @ApiResponse(responseCode = "204", description = "Customer deleted successfully")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String cpf) {
        customerService.deleteCustomer(cpf);
        return ResponseEntity.noContent().build();
    }
}
