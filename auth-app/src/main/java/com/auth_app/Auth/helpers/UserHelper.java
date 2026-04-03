package com.auth_app.Auth.helpers;

import java.util.UUID;

public class UserHelper {
    public static UUID parseUUID(String userId) {
        return UUID.fromString(userId);
    }
}
