package io.vicarius.quota.handler;

import io.vicarius.quota.mapper.UserMapper;
import io.vicarius.quota.repository.ElasticsearchUserRepository;
import io.vicarius.quota.repository.MysqlUserRepository;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * Used to allow the creation of a UserHandler instance depending on the time of day.
 */
@RequiredArgsConstructor
public class UserHandlerFactorybean implements FactoryBean<UserHandler> {

    @Value("${application.day-time.start}")
    LocalTime dayTimeStart;

    @Value("${application.day-time.end}")
    LocalTime dayTimeEnd;

    private final UserMapper userMapper;
    private final MysqlUserRepository mysqlUserRepository;
    private final ElasticsearchUserRepository elasticsearchUserRepository;

    @Override
    public UserHandler getObject() {
        if (isDailyTime()) {
            return new NightlyUserHandler(userMapper, elasticsearchUserRepository);
        }
        return new DayUserHandler(userMapper, mysqlUserRepository);
    }

    @Override
    public Class<?> getObjectType() {
        return UserHandler.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    private boolean isDailyTime() {
        LocalTime now = LocalTime.now();
        return (now.equals(dayTimeStart) || now.isAfter(dayTimeStart)) && (now.equals(dayTimeEnd) || now.isBefore(dayTimeEnd));
    }

}
