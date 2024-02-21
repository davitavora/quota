package io.vicarius.quota.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class QuotaLimitReachedException extends ErrorResponseException {

    public QuotaLimitReachedException(String identifier) {
        super(HttpStatus.BAD_REQUEST, asProblemDetail(identifier), null);
    }

    private static ProblemDetail asProblemDetail(String identifier) {
        final var message = "quota limit reached for user '%s'".formatted(identifier);
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message);
    }

}
