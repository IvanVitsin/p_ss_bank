package com.bank.transfer.controller;

import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.service.AccountTransferService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api
@RestController
@Tag(name = "Перевод по номеру счета", description = "Контроллер, отвечающий за операции с переводом по номеру банковского счета")
@RequiredArgsConstructor
public class AccountTransferController {

    private final AccountTransferService accountTransferService;

    @GetMapping("/account")
    @Operation(summary = "Получение всех переводов на банковские счета")
    public ResponseEntity<List<AccountTransferDto>> getAccountTransfers() {
        return new ResponseEntity<>(accountTransferService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/account/{accountNumber}")
    @Operation(summary = "Получение перевода по номеру банковского счета")
    public ResponseEntity<AccountTransferDto> getAccountTransferByAccountNumber(@PathVariable  @Parameter(description = "Номер счета")Long accountNumber) {
        return new ResponseEntity<>(accountTransferService.findByAccountNumber(accountNumber), HttpStatus.OK);
    }

    @PostMapping("/account")
    @Operation(summary = "Создание перевода")
    public ResponseEntity<AccountTransferDto> createAccountTransfer(@RequestBody @Valid AccountTransferDto accountTransferDto) {
        accountTransferService.save(accountTransferDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/account/{accountDetailsId}")
    @Operation(summary = "Удаление перевода")
    public ResponseEntity<String> deleteAccountTransfer(@PathVariable @Parameter(description = "Идентификатор перевода") Long accountDetailsId) {
        accountTransferService.delete(accountDetailsId);
        return new ResponseEntity<>("Перевод успешно удален", HttpStatus.OK);
    }
}
