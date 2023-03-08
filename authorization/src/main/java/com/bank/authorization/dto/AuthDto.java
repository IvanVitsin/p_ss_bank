package com.bank.authorization.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Объект передачи данных(DTO)
 * @author Zhukova Ekaterina
 * @version 1.0
 * @since 15.02.2023
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthDto {
    private long id;

    private String role;

    private long profile_id;
}
