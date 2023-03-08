package com.bank.transfer.service;

import com.bank.transfer.dto.CardTransferDto;

import java.util.List;

public interface CardTransferService {

    void save(CardTransferDto cardTransferDto);

    void delete(Long accountDetailsId);

    CardTransferDto findByCardNumber(Long cardNumber);

    List<CardTransferDto> findAll();
}
