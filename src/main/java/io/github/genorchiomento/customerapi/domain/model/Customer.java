package io.github.genorchiomento.customerapi.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
public record Customer(
        @Id String id,
        String cpf,
        String name,
        String birthDate,
        String phone,
        String address
) {
}
