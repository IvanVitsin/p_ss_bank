package com.bank.transfer.service;

import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.exception.NotFoundException;
import com.bank.transfer.mapper.AutoCardTransferMapper;
import com.bank.transfer.model.CardTransfer;
import com.bank.transfer.repository.CardTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardTransferServiceImpl implements CardTransferService {

    private final CardTransferRepository cardTransferRepository;


    @Override
    @Transactional
    public void save(@Valid CardTransferDto cardTransferDto) {

        cardTransferRepository.save(AutoCardTransferMapper.CARD_TRANSFER_MAPPER.mapToCardTransfer(cardTransferDto));
    }

    @Override
    @Transactional
    public void delete(Long accountDetailsId) {
        cardTransferRepository.deleteCardTransferByAccountDetailsId(accountDetailsId);
    }

    @Override
    public CardTransferDto findByCardNumber(Long cardNumber) {
        Optional<CardTransfer> cardTransfer = cardTransferRepository.findByCardNumber(cardNumber);
        if (cardTransfer.isEmpty()) {
            throw new NotFoundException("Перевод по этому номеру карты: " + cardNumber+ " не найден");
        }
        return AutoCardTransferMapper.CARD_TRANSFER_MAPPER.mapToCardTransferDto(cardTransfer.get());
    }

    @Override
    public List<CardTransferDto> findAll() {
        return AutoCardTransferMapper.CARD_TRANSFER_MAPPER.mapToAllCardTransferDtos(cardTransferRepository.findAll());
    }
}

