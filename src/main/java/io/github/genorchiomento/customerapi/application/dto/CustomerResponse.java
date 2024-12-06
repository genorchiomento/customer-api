package io.github.genorchiomento.customerapi.application.dto;

public record CustomerResponse(
        String id,
        String cpf,
        String name,
        String birthDate,
        String phone,
        String address
) {
}
