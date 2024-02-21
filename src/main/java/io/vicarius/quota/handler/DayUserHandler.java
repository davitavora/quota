package io.vicarius.quota.handler;

import io.vicarius.quota.mapper.UserMapper;
import io.vicarius.quota.model.entity.User;
import io.vicarius.quota.model.payload.UserPayload;
import io.vicarius.quota.model.representation.UserRepresentation;
import io.vicarius.quota.repository.MysqlUserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class DayUserHandler implements UserHandler {

    private final UserMapper mapper;
    private final MysqlUserRepository mysqlUserRepository;

    @Override
    public UserRepresentation create(UserPayload createUserCommand) {
        User user = mapper.toEntity(createUserCommand);
        user = mysqlUserRepository.save(user);
        return mapper.toRepresentation(user);
    }

    @Override
    @Transactional
    public void update(String id, UserPayload userPayload) {
        mysqlUserRepository.findById(id).ifPresent(user -> {
            user.setFirstName(userPayload.firstName());
            user.setLastName(userPayload.lastName());
        });
    }

    @Override
    public Optional<UserRepresentation> findBy(String id) {
        return mysqlUserRepository.findById(id).map(mapper::toRepresentation);
    }

    @Override
    public void delete(String id) {
        mysqlUserRepository.deleteById(id);
    }

}
