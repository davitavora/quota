package io.vicarius.quota.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.vicarius.quota.exception.QuotaLimitReachedException;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class QuotaServiceTest {

    private final QuotaService systemUnderTest = new TestableQuotaService();

    @Test
    void shouldCreateQuotaWithDefaultValueIfEntryDoNotExists() {
        assertThat(systemUnderTest.findQuotas()).isEmpty();

        String id = UUID.randomUUID().toString();
        systemUnderTest.consume(id);

        assertThat(systemUnderTest.findQuotas()).containsEntry(id, 1);
    }

    @Test
    void shouldIncreaseQuotaValueWhenConsumed() {
        String id = UUID.randomUUID().toString();
        systemUnderTest.consume(id);

        assertThat(systemUnderTest.findQuotas()).containsEntry(id, 1);
        systemUnderTest.consume(id);
        assertThat(systemUnderTest.findQuotas()).containsEntry(id, 2);
    }

    @Test
    void shouldFailToConsumeWhenQuotaLimitIsReached() {
        String id = UUID.randomUUID().toString();
        systemUnderTest.consume(id);
        systemUnderTest.consume(id);
        systemUnderTest.consume(id);
        systemUnderTest.consume(id);
        systemUnderTest.consume(id);
        assertThat(systemUnderTest.findQuotas()).containsEntry(id, 5);
        assertThatThrownBy(() -> systemUnderTest.consume(id))
            .isInstanceOf(QuotaLimitReachedException.class)
            .hasMessageContaining("quota limit reached for user '%s'", id);
    }

    @Test
    void shouldRemoveQuota() {
        String id = UUID.randomUUID().toString();
        systemUnderTest.consume(id);
        assertThat(systemUnderTest.findQuotas()).containsEntry(id, 1);
        systemUnderTest.removeQuota(id);
        assertThat(systemUnderTest.findQuotas()).isEmpty();
    }

    static class TestableQuotaService extends QuotaService {

        @Override
        protected Integer getQuotaLimit() {
            return 5;
        }

    }

}
