package com.bank.authorization.controller;

import com.bank.authorization.dto.AuthDto;
import com.bank.authorization.entity.Auth;
import com.bank.authorization.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@AllArgsConstructor
@Tag(name = "Авторизация", description = "Данные о правах пользователей")
public class AuthController {

    private AuthService authService;

    @Operation(summary = "Добавить новую запись",
            description = "Добавление нового пользователя с правами")
    @ApiResponse(responseCode = "200", description = "Добавлен новый пользователь")
    @PostMapping("/add")
    public ResponseEntity<AuthDto> createAuth(@RequestBody Auth auth) {
        AuthDto savedAuth = authService.createAuth(auth);
        return new ResponseEntity<>(savedAuth, HttpStatus.CREATED);
    }

    @Operation(summary = "Получение роли пользователя",
            description = "Получение роли пользователя по id")
    @ApiResponse(responseCode = "200", description = "Успешно получена запись о роли пользователя")
    @GetMapping("{id}")
    public ResponseEntity<AuthDto> getUserById(@PathVariable("id") Long id) {
        AuthDto authDto = authService.getUserById(id);
        return new ResponseEntity<>(authDto, HttpStatus.OK);
    }

    @Operation(summary = "Получить все записи",
            description = "Все записи пользователей")
    @ApiResponse(responseCode = "200", description = "Успешно получены все записи")
    @GetMapping
    public ResponseEntity<List<AuthDto>> getAllRoles() {
        List<AuthDto> roles = authService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @Operation(summary = "Обновить роль пользователя",
            description = "Обновление роли пользователя")
    @ApiResponse(responseCode = "200", description = "Роль пользователя обновлена")
    @PutMapping("{id}")
    public ResponseEntity<AuthDto> updateAuth(@PathVariable("id") Long id, @RequestBody AuthDto authDto) {
        authDto.setProfile_id(id);
        AuthDto updatedAuth = authService.updateAuth(authDto);
        return new ResponseEntity<>(updatedAuth, HttpStatus.OK);
    }

    @Operation(summary = "Удалить информацию о пользователе",
            description = "Удаление информации о пользователе по id")
    @ApiResponse(responseCode = "200", description = "Удалена информация о пользователе")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAuth(@PathVariable("id") Long id) {
        authService.deleteAuth(id);
        return new ResponseEntity<>("Auth deleted!", HttpStatus.OK);
    }
}
