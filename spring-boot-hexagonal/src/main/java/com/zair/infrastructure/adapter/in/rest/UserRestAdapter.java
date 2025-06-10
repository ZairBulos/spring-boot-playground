package com.zair.infrastructure.adapter.in.rest;

import com.zair.application.port.in.UserServicePort;
import com.zair.infrastructure.adapter.in.rest.dto.CreateUserRequest;
import com.zair.infrastructure.adapter.in.rest.dto.UserResponse;
import com.zair.infrastructure.adapter.in.rest.mapper.UserRestMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestAdapter {
    private final UserServicePort service;
    private final UserRestMapper mapper;

    public UserRestAdapter(UserServicePort service, UserRestMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAll().stream().map(mapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResponse(service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(service.create(mapper.toCommand(request))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> create(@PathVariable Long id, @Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResponse(service.update(id, mapper.toCommand(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
