package io.vicarius.quota.model.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserPayload(
    @NotBlank
    @Size(max = 255)
    String firstName,
    @NotBlank
    @Size(max = 255)
    String lastName
) {

}
