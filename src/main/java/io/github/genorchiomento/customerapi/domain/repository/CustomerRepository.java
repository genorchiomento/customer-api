package io.github.genorchiomento.customerapi.domain.repository;

import io.github.genorchiomento.customerapi.domain.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<Customer> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
