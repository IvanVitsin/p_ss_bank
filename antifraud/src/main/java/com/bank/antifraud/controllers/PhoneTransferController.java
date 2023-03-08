package com.bank.antifraud.controllers;
import com.bank.antifraud.Exception.NotFoundException;
import com.bank.antifraud.dto.PhoneDTO;
import com.bank.antifraud.repository.PhoneTransferRepository;
import com.bank.antifraud.service.PhoneTransferService;
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
@RequestMapping("/api/phone-transfer")
@Api("подозрительные операции по номеру телефона")
@Tag(name = "Подозрительные переводы по номеру телефона")
public class PhoneTransferController {

    private final PhoneTransferService phoneTransferService;
    private final PhoneTransferRepository phoneTransferRepository;


    @PostMapping
    @ApiOperation("создание новой подозрительной транзакции")
    public ResponseEntity<PhoneDTO> addPhoneTransfer(PhoneDTO phoneDTO) {
        return new ResponseEntity<>(phoneTransferService
                .saveAccountTransfer(phoneDTO),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("получение подозрительного перевода по id ")
    public ResponseEntity<PhoneDTO> getPhoneTransferById(@PathVariable Long id) {
        ResponseEntity<PhoneDTO> entity = new ResponseEntity<>(phoneTransferService.findAccountTransferById(id), HttpStatus.OK);
        if (entity == null) {
            throw new NotFoundException("No transfer with such ID found");
        }
        return entity;
    }

    @GetMapping
    @ApiOperation("получение всех подозрительных переводов по телефонам")
    public ResponseEntity<List<PhoneDTO>> getPhoneDetailsAll() {
        return new ResponseEntity<>(phoneTransferService.getAllAccounts(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ApiOperation("удаление подозрительного перевода по id")
    public void deletePhoneTransfer(@PathVariable Long id) {
        if (phoneTransferService.findAccountTransferById(id) == null) {
            throw new NotFoundException("No transfer with such ID found");
        }
        phoneTransferService.deleteAccountTransfer(id);
        System.out.printf("user with id: %d was deleted", id);
    }


}
