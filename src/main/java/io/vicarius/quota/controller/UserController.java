package io.vicarius.quota.controller;

import io.vicarius.quota.model.payload.UserPayload;
import io.vicarius.quota.model.representation.UserRepresentation;
import io.vicarius.quota.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

// TODO: Improve validation messages with ApplicationMessages.properties
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(
    value = "users"
)
@RequestScope
public class UserController {

    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserRepresentation create(@Valid @RequestBody UserPayload userPayload) {
        return service.create(userPayload);
    }

    @GetMapping(path = "{id}")
    public UserRepresentation findBy(@PathVariable String id) {
        return service.findBy(id);
    }

    // TODO: Create a PatchMapping method to allow partial updates to the "user" resource, using JsonPatch(application/json-patch+json) or JsonMerge(application/merge-patch+json)
    @PutMapping(path = "{id}")
    public void update(@PathVariable String id, @Valid @RequestBody UserPayload userPayload) {
        service.update(id, userPayload);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @PutMapping(path = "{id}/quota")
    // I would probably do it another way. Currently, API quota consumption is necessarily associated with a REST call,
    // made manually. A better approach would be to use, for example, a OncePerRequestFilter/HandlerInterceptor or even an Aspect to capture
    // some type of identifier (Http Header, JWT 'subject' attribute) of the user making the HTTP call and this way the
    // quota control procedure would be transparent to the API.
    public void consumeQuota(@PathVariable String id) {
        service.consumeQuota(id);
    }


}
