package hu.devoli.springbackend.records;

import java.util.UUID;

public record UserResponse(UUID id, String Name, String email) {}
