package io.vicarius.quota.model.representation;

import java.time.LocalDateTime;

public record UserRepresentation(
    String id,
    String firstName,
    String lastName,
    LocalDateTime lastLoginTimeUtc
) {
}
