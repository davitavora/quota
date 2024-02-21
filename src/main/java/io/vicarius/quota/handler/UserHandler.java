package io.vicarius.quota.handler;

import io.vicarius.quota.model.payload.UserPayload;
import io.vicarius.quota.model.representation.UserRepresentation;
import java.util.Optional;

public interface UserHandler {

    UserRepresentation create(UserPayload createUserCommand);

    void update(String id, UserPayload userPayload);

    Optional<UserRepresentation> findBy(String id);

    void delete(String id);

}
