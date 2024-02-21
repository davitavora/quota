package io.vicarius.quota.service;

import io.vicarius.quota.exception.UserNotFoundException;
import io.vicarius.quota.model.payload.UserPayload;
import io.vicarius.quota.model.representation.UserRepresentation;
import io.vicarius.quota.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

@Service
@RequestScope
@RequiredArgsConstructor
public class UserService {

    private final UserHandler repository;
    private final QuotaService quotaService;

    public UserRepresentation create(UserPayload userPayload) {
        return repository.create(userPayload);
    }

    @Transactional
    public void update(String id, UserPayload userPayload) {
        repository.update(id, userPayload);
    }

    public UserRepresentation findBy(String id) {
        return repository.findBy(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public void delete(String id) {
        repository.delete(id);
        quotaService.removeQuota(id);
    }

    public void consumeQuota(String id) {
        quotaService.consume(id);
    }

}
