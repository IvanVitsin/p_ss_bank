package com.bank.antifraud.service;

import com.bank.antifraud.Exception.NotFoundException;
import com.bank.antifraud.dto.AccountDTO;
import com.bank.antifraud.entity.AccountTransferEntity;
import com.bank.antifraud.mapper.AllEntityMapper;
import com.bank.antifraud.repository.AccountTransferRepository;
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
public class AccountTransferService {
    private final AccountTransferRepository accountTransferRepository;


    @Transactional
    public List<AccountDTO> getAllAccounts() {
        if (accountTransferRepository == null) {
            log.error("does not exist");
            throw new NotFoundException("accountTransferRepository is null");
        }
        List<AccountTransferEntity> accounts = accountTransferRepository.findAll();
        if (accounts.isEmpty()) {
            log.error("does not exist");
            throw new NotFoundException("No  Actual transfers found");
        }
        log.info("Данные о подозрительных транзакциях предоставлены");
        return accounts
                .stream()
                .map(AllEntityMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
    }



    @Transactional
    public void deleteAccountTransfer(Long id) {
        Optional<AccountTransferEntity> accountDetails = accountTransferRepository.findById(id);
        if (accountDetails.isEmpty()) {
            log.error("does not exist");
            throw new NotFoundException("No account transfer found with such id");
        }
        accountTransferRepository.deleteById(id);
        log.info("Данные о транзакции удалены из базы данных");
    }

    @Transactional
    public AccountDTO findAccountTransferById(Long id) {
        AccountTransferEntity entity =  accountTransferRepository.findById(id).orElse(null);
        if (entity== null) {
            log.error("does not exist");
            throw new NotFoundException("Транзакция не найдена");
        }
        accountTransferRepository.save(entity);
        return AllEntityMapper.MAPPER.toDTO(entity);
    }


    @Transactional
    public AccountDTO saveAccountTransfer(AccountDTO accountDTO) {
        AccountTransferEntity entity = AllEntityMapper.MAPPER.toEntity(accountDTO);
        accountTransferRepository.saveAndFlush(entity);
        log.info("Данные о транзакции добавлены в базу данных");
        return AllEntityMapper.MAPPER.toDTO(entity);
    }


}
