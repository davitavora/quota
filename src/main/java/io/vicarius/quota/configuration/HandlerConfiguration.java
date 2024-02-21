package io.vicarius.quota.configuration;

import io.vicarius.quota.mapper.UserMapper;
import io.vicarius.quota.repository.ElasticsearchUserRepository;
import io.vicarius.quota.repository.MysqlUserRepository;
import io.vicarius.quota.handler.UserHandlerFactorybean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfiguration {

    @Bean
    public UserHandlerFactorybean userRepositoryFactoryBean(UserMapper userMapper,
                                                            MysqlUserRepository mysqlUserRepository,
                                                            ElasticsearchUserRepository elasticsearchUserRepository) {
        return new UserHandlerFactorybean(userMapper, mysqlUserRepository, elasticsearchUserRepository);
    }

}
