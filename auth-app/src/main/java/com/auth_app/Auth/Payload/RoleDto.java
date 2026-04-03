package com.auth_app.Auth.Payload;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private UUID id;
    private String name;
}
