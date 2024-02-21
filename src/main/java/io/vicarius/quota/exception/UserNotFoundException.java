package io.vicarius.quota.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class UserNotFoundException extends ErrorResponseException {

    public UserNotFoundException(String identifier) {
        super(HttpStatus.NOT_FOUND, asProblemDetail(identifier), null);
    }

    private static ProblemDetail asProblemDetail(String identifier) {
        final var message = "Unable to find user with id %s".formatted(identifier);
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, message);
    }

}
