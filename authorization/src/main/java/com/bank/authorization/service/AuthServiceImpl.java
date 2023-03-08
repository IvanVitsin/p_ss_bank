package com.bank.authorization.service;

import com.bank.authorization.dto.AuthDto;
import com.bank.authorization.entity.Auth;
import com.bank.authorization.mapper.AutoAuthMapper;
import com.bank.authorization.repository.AuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация CRUD операций интерфейса AuthService
 * @author Zhukova Ekaterina
 * @version 1.0
 * @since 15.02.2023
 */

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthRepository authRepository;

    @Override
    public AuthDto createAuth(Auth auth) {
        return AutoAuthMapper.MAPPER.mapToAuthDto(authRepository.save(auth));
    }

    @Override
    public AuthDto getUserById(Long id) {
        List<Auth> auths = authRepository.findAll();
        Auth auth = new Auth();
        for (Auth el : auths) {
            if (id == el.getProfile_id()) {
                auth = el;
                break;
            }
        }
        return AutoAuthMapper.MAPPER.mapToAuthDto(auth);
    } //TODO Optional

    @Override
    public List<AuthDto> getAllRoles() {
        return authRepository.findAll()
                .stream().map(AutoAuthMapper.MAPPER::mapToAuthDto).collect(Collectors.toList());
    }

    @Override
    public AuthDto updateAuth(AuthDto auth) {
        List<Auth> auths = authRepository.findAll();
        Auth updatedAuth = new Auth();
        for (Auth el : auths) {
            if (el.getProfile_id() == auth.getProfile_id()) {
                el.setRole(auth.getRole());
                updatedAuth = authRepository.save(el);
                break;
            }
        }
        return AutoAuthMapper.MAPPER.mapToAuthDto(updatedAuth);
    }

    @Override
    public void deleteAuth(Long id) {
        List<Auth> auths = authRepository.findAll();
        for (Auth el : auths) {
            if (el.getProfile_id() == id) {
                authRepository.delete(el);
                break;
            }
        }
    }
}
