package com.bank.antifraud.controllers;
import com.bank.antifraud.Exception.NotFoundException;
import com.bank.antifraud.dto.CardDTO;
import com.bank.antifraud.repository.CardTransferRepository;
import com.bank.antifraud.service.CardTransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/card-transfer")
@Api("подозрительные операции по карте")
@Tag(name = "Подозрительные переводы по номеру карты")
public class CardTransferController {

    private final CardTransferService cardTransferService;
    private final CardTransferRepository cardTransferRepository;



    @PostMapping
    @ApiOperation("создание новой подозрительной транзакции")
    public ResponseEntity<CardDTO> addAccountTransfer(CardDTO cardDTO) {
        return new ResponseEntity<>(cardTransferService
                .saveAccountTransfer(cardDTO),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("получение подозрительного перевода по id ")
    public ResponseEntity<CardDTO> getAccountTransferById(@PathVariable Long id) {
        ResponseEntity<CardDTO> entity = new ResponseEntity<>(cardTransferService.findAccountTransferById(id), HttpStatus.OK);
        if (entity == null) {
            throw new NotFoundException("No transfer with such ID found");
        }
        return entity;
    }

    @GetMapping
    @ApiOperation("получение всех подозрительных переводов по картам")
    public ResponseEntity<List<CardDTO>> getAccountDetailsAll() {
        return new ResponseEntity<>(cardTransferService.getAllAccounts(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ApiOperation("удаление подозрительного перевода по id")
    public void deleteAccountTransfer(@PathVariable Long id) {
        if (cardTransferService.findAccountTransferById(id) == null) {
            throw new NotFoundException("No transfer with such ID found");
        }
        cardTransferService.deleteAccountTransfer(id);
        System.out.printf("user with id: %d was deleted", id);
    }

}
