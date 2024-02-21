package io.vicarius.quota.mapper;

import io.vicarius.quota.model.payload.UserPayload;
import io.vicarius.quota.model.document.UserDocument;
import io.vicarius.quota.model.entity.User;
import io.vicarius.quota.model.representation.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastLoginTimeUtc", ignore = true)
    User toEntity(UserPayload userPayload);

    UserRepresentation toRepresentation(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastLoginTimeUtc", ignore = true)
    UserDocument toDocument(UserPayload userPayload);

    UserRepresentation toRepresentation(UserDocument user);

}
