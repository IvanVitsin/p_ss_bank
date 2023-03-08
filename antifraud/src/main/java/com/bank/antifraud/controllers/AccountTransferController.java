package com.bank.antifraud.controllers;

import com.bank.antifraud.Exception.NotFoundException;
import com.bank.antifraud.dto.AccountDTO;
import com.bank.antifraud.repository.AccountTransferRepository;
import com.bank.antifraud.service.AccountTransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ivan Vitsin
 *
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/account-transfer")
@Tag(name = "Подозрительные переводы по номеру аккаунта")
@Api("контроллер Для подозрительных переводов на Аккаунте")
public class AccountTransferController {

    private final AccountTransferService accountTransferService;
    private final AccountTransferRepository accountTransferRepository;



    @PostMapping
    @ApiOperation("создание новой подозрительной транзакции")
    public ResponseEntity<AccountDTO> addAccountTransfer(AccountDTO accountDTO) {
        return new ResponseEntity<>(accountTransferService
                .saveAccountTransfer(accountDTO),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("получение подозрительной транзакции по номеру аккаунта")
    public ResponseEntity<AccountDTO> getAccountTransferById(@PathVariable Long id) {
        ResponseEntity<AccountDTO> entity = new ResponseEntity<>(accountTransferService.findAccountTransferById(id), HttpStatus.OK);
        if (entity == null) {
            throw new NotFoundException("No transfer with such ID found");
        }
        return entity;
    }

    @GetMapping
    @ApiOperation("получение всех подозрительных транзакций")
    public ResponseEntity<List<AccountDTO>> getAccountDetailsAll() {
        return new ResponseEntity<>(accountTransferService.getAllAccounts(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ApiOperation("удаление информации о подозрительной транзакции")
    public void deleteAccountTransfer(@PathVariable Long id) {
        if (accountTransferService.findAccountTransferById(id) == null) {
            throw new NotFoundException("No transfer with such ID found");
        }
        accountTransferService.deleteAccountTransfer(id);
        System.out.printf("user with id: %d was deleted", id);
    }
}
