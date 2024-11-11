package com.actvn.Shopee_BE.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequest {
    String username;
    String email;
    Set<String> roles;
    String password;
}
