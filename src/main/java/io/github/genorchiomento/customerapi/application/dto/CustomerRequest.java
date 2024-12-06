package io.github.genorchiomento.customerapi.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerRequest(
        @NotBlank(message = "CPF is mandatory")
        @Pattern(regexp = "\\d{11}", message = "CPF must contain exactly 11 digits")
        String cpf,

        @NotBlank(message = "Name is mandatory")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Birth Date is mandatory")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Birth Date must be in the format YYYY-MM-DD")
        String birthDate,

        @NotBlank(message = "Phone number is mandatory")
        @Pattern(regexp = "\\d{10,11}", message = "Phone number must contain 10 or 11 digits")
        String phone,

        @NotBlank(message = "Address is mandatory")
        @Size(max = 255, message = "Address must not exceed 255 characters")
        String address
) {
}
