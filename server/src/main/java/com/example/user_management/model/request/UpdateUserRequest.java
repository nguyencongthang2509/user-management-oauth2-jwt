package com.example.user_management.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateUserRequest extends BaseUserRequest {

    @NotBlank
    private UUID id;

    @NotBlank
    private UUID idUserCurrent;
}
