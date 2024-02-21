package io.vicarius.quota.service;

import io.vicarius.quota.exception.QuotaLimitReachedException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuotaService {

    public static final int INCREMENT_VALUE_PER_REQUEST = 1;

    @Value("${application.quota.limit}")
    Integer quotaLimit;

    private final Map<String, Integer> quotas = new ConcurrentHashMap<>();

    public void consume(String id) {
        if (limitReached(id)) {
            throw new QuotaLimitReachedException(id);
        }
        quotas.merge(id, INCREMENT_VALUE_PER_REQUEST, Math::addExact);
    }

    private boolean limitReached(String id) {
        return quotas.containsKey(id) && getQuotaLimit().equals(quotas.get(id));
    }

    public Map<String, Integer> findQuotas() {
        return quotas;
    }

    protected Integer getQuotaLimit() {
        return quotaLimit;
    }

    public void removeQuota(String id) {
        quotas.remove(id);
    }

}
