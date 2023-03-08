package com.bank.authorization.service;


import com.bank.authorization.dto.AuthDto;
import com.bank.authorization.entity.Auth;

import java.util.List;

/**
 * CRUD операции с БД
 * @author Zhukova Ekaterina
 * @version 1.0
 * @since 15.02.2023
 */

public interface AuthService {
    AuthDto createAuth(Auth auth);

    AuthDto getUserById(Long id);

    List<AuthDto> getAllRoles();

    AuthDto updateAuth(AuthDto auth);

    void deleteAuth(Long id);
}
