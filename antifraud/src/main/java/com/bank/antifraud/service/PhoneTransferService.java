package com.bank.antifraud.service;

import com.bank.antifraud.Exception.NotFoundException;
import com.bank.antifraud.dto.PhoneDTO;
import com.bank.antifraud.entity.PhoneTransferEntity;
import com.bank.antifraud.mapper.AllEntityMapper;
import com.bank.antifraud.repository.PhoneTransferRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PhoneTransferService {


    private final PhoneTransferRepository phoneTransferRepository;


    @Transactional
    public List<PhoneDTO> getAllAccounts() {
        if (phoneTransferRepository == null) {
            log.error("does not exist");
            throw new NotFoundException("accountTransferRepository is null");
        }
        List<PhoneTransferEntity> phones = phoneTransferRepository.findAll();
        if (phones.isEmpty()) {
            log.error("does not exist");
            throw new NotFoundException("No  Actual transfers found");
        }
        log.info("Данные о подозрительных транзакциях предоставлены");
        return phones
                .stream()
                .map(AllEntityMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
    }



    @Transactional
    public void deleteAccountTransfer(Long id) {
        Optional<PhoneTransferEntity> accountDetails = phoneTransferRepository.findById(id);
        if (accountDetails.isEmpty()) {
            log.error("does not exist");
            throw new NotFoundException("No account transfer found with such id");
        }
        phoneTransferRepository.deleteById(id);
        log.info("Данные о транзакции удалены из базы данных");
    }

    @Transactional
    public PhoneDTO findAccountTransferById(Long id) {
        PhoneTransferEntity entity =  phoneTransferRepository.findById(id).orElse(null);
        if (entity== null) {
            log.error("does not exist");
            throw new NotFoundException("Транзакция не найдена");
        }
        phoneTransferRepository.save(entity);
        return AllEntityMapper.MAPPER.toDTO(entity);
    }


    @Transactional
    public PhoneDTO saveAccountTransfer(PhoneDTO phoneDTO) {
        PhoneTransferEntity entity = AllEntityMapper.MAPPER.toEntity(phoneDTO);
        phoneTransferRepository.saveAndFlush(entity);
        log.info("Данные о транзакции добавлены в базу данных");
        return AllEntityMapper.MAPPER.toDTO(entity);
    }

}
