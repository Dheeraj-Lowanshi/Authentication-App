package com.auth_app.Auth.Payload;

public record LoginRequest(
    String email,
    String password
) {
}
