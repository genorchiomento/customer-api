package io.github.genorchiomento.customerapi.application.controller;

import io.github.genorchiomento.customerapi.application.dto.CustomerRequest;
import io.github.genorchiomento.customerapi.application.dto.CustomerResponse;
import io.github.genorchiomento.customerapi.application.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer", description = "Customer management APIs")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @Operation(summary = "Create a new customer", description = "Creates a new customer with the provided information")
    @ApiResponse(responseCode = "201", description = "Customer created successfully")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @GetMapping
    @Operation(summary = "Get all customers", description = "Retrieves a list of all customers")
    @ApiResponse(responseCode = "200", description = "List of customers retrieved successfully")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }


//    @GetMapping("/{cpf}")
//    @Operation(summary = "Get a customer by CPF", description = "Retrieves a customer's information based on their CPF")
//    @ApiResponse(responseCode = "200", description = "Customer found")
//    @ApiResponse(responseCode = "404", description = "Customer not found")
//    public ResponseEntity<Customer> getCustomer(@PathVariable String cpf) {
//        return customerService.getCustomer(cpf)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

//    @PutMapping("/{cpf}")
//    @Operation(summary = "Update a customer", description = "Updates an existing customer's information")
//    @ApiResponse(responseCode = "200", description = "Customer updated successfully")
//    @ApiResponse(responseCode = "404", description = "Customer not found")
//    public ResponseEntity<Customer> updateCustomer(@PathVariable String cpf, @RequestBody Customer customer) {
//        Customer updatedCustomer = customerService.updateCustomer(cpf, customer);
//        return ResponseEntity.ok(updatedCustomer);
//    }

//    @DeleteMapping("/{cpf}")
//    @Operation(summary = "Delete a customer", description = "Deletes a customer based on their CPF")
//    @ApiResponse(responseCode = "204", description = "Customer deleted successfully")
//    @ApiResponse(responseCode = "404", description = "Customer not found")
//    public ResponseEntity<Void> deleteCustomer(@PathVariable String cpf) {
//        customerService.deleteCustomer(cpf);
//        return ResponseEntity.noContent().build();
//    }
}