package com.bank.antifraud.service;

import com.bank.antifraud.Exception.NotFoundException;
import com.bank.antifraud.dto.CardDTO;
import com.bank.antifraud.entity.CardTransferEntity;
import com.bank.antifraud.mapper.AllEntityMapper;
import com.bank.antifraud.repository.CardTransferRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CardTransferService {

    private final CardTransferRepository cardTransferRepository;


    @Transactional
    public List<CardDTO> getAllAccounts() {
        if (cardTransferRepository == null) {
            log.error("does not exist");
            throw new NotFoundException("accountTransferRepository is null");
        }
        List<CardTransferEntity> cards = cardTransferRepository.findAll();
        if (cards.isEmpty()) {
            log.error("does not exist");
            throw new NotFoundException("No  Actual transfers found");
        }
        log.info("Данные о подозрительных транзакциях предоставлены");
        return cards
                .stream()
                .map(AllEntityMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
    }



    @Transactional
    public void deleteAccountTransfer(Long id) {
        Optional<CardTransferEntity> accountDetails = cardTransferRepository.findById(id);
        if (accountDetails.isEmpty()) {
            log.error("does not exist");
            throw new NotFoundException("No account transfer found with such id");
        }
        cardTransferRepository.deleteById(id);
        log.info("Данные о транзакции удалены из базы данных");
    }

    @Transactional
    public CardDTO findAccountTransferById(Long id) {
        CardTransferEntity entity =  cardTransferRepository.findById(id).orElse(null);
        if (entity== null) {
            log.error("does not exist");
            throw new NotFoundException("Транзакция не найдена");
        }
        cardTransferRepository.save(entity);
        return AllEntityMapper.MAPPER.toDTO(entity);
    }


    @Transactional
    public CardDTO saveAccountTransfer(CardDTO cardDTO) {
        CardTransferEntity entity = AllEntityMapper.MAPPER.toEntity(cardDTO);
        cardTransferRepository.saveAndFlush(entity);
        log.info("Данные о транзакции добавлены в базу данных");
        return AllEntityMapper.MAPPER.toDTO(entity);
    }

}
