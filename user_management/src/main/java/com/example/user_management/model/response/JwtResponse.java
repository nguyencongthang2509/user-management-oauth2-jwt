package com.example.user_management.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * @author thangncph26123
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private UUID idUser;

    private String fullName;

    private String email;

    private Integer role;

    private String token;
}
