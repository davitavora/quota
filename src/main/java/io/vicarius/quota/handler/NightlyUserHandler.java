package io.vicarius.quota.handler;

import io.vicarius.quota.mapper.UserMapper;
import io.vicarius.quota.model.document.UserDocument;
import io.vicarius.quota.model.payload.UserPayload;
import io.vicarius.quota.model.representation.UserRepresentation;
import io.vicarius.quota.repository.ElasticsearchUserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class NightlyUserHandler implements UserHandler {

    private final UserMapper mapper;
    private final ElasticsearchUserRepository elasticsearchUserRepository;

    @Override
    public UserRepresentation create(UserPayload createUserCommand) {
        UserDocument user = mapper.toDocument(createUserCommand);
        user = elasticsearchUserRepository.save(user);
        return mapper.toRepresentation(user);
    }

    @Override
    @Transactional
    public void update(String id, UserPayload userPayload) {
        elasticsearchUserRepository.findById(id).ifPresent(user -> {
            user.setFirstName(userPayload.firstName());
            user.setLastName(userPayload.lastName());
        });
    }

    @Override
    public Optional<UserRepresentation> findBy(String id) {
        return elasticsearchUserRepository.findById(id).map(mapper::toRepresentation);
    }

    @Override
    public void delete(String id) {
        elasticsearchUserRepository.deleteById(id);
    }

}
