package com.bank.transfer.controller;

import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.service.CardTransferService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardTransferController {

    private final CardTransferService cardTransferService;

    @GetMapping("/card")
    public ResponseEntity<List<CardTransferDto>> getCardTransfers() {
        return new ResponseEntity<>(cardTransferService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/card/{cardNumber}")
    public ResponseEntity<CardTransferDto> getCarTransferByCardNumber(@PathVariable Long cardNumber) {
        return new ResponseEntity<>(cardTransferService.findByCardNumber(cardNumber), HttpStatus.OK);
    }

    @PostMapping("/card")
    public ResponseEntity<CardTransferDto> createCardTransfer(@RequestBody @Valid CardTransferDto cardTransferDto) {
        cardTransferService.save(cardTransferDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/card/{id}")
    public ResponseEntity<String> deleteCardTransfer(@PathVariable Long id) {
        cardTransferService.delete(id);
        return new ResponseEntity<>("Перевод успешно удален", HttpStatus.OK);
    }
}
