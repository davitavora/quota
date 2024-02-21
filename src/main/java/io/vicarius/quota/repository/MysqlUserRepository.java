package io.vicarius.quota.repository;

import io.vicarius.quota.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlUserRepository extends CrudRepository<User, String> {

}
