package io.github.genorchiomento.customerapi.domain.repository;

import io.github.genorchiomento.customerapi.domain.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
