package com.bank.transfer.controller;

import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.service.PhoneTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PhoneTransferController {

    private final PhoneTransferService phoneTransferService;

    @GetMapping("/phone")
    public ResponseEntity<List<PhoneTransferDto>> getPhoneTransfers() {
        return new ResponseEntity<>(phoneTransferService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<PhoneTransferDto> getPhoneTransferByPhoneNumber(@PathVariable Long phoneNumber) {
        return new ResponseEntity<>(phoneTransferService.findByPhoneNumber(phoneNumber), HttpStatus.OK);
    }

    @PostMapping("/phone")
    public ResponseEntity<PhoneTransferDto> createPhoneTransfer(@RequestBody @Valid PhoneTransferDto phoneTransferDto) {
        phoneTransferService.save(phoneTransferDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/phone/{id}")
    public ResponseEntity<String> deletePhoneTransfer(@PathVariable Long id) {
        phoneTransferService.delete(id);
        return new ResponseEntity<>("Перевод успешно удален", HttpStatus.OK);
    }
}
