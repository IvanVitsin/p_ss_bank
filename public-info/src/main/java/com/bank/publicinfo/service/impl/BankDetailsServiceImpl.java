package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.entity.BankDetailsEntity;
import com.bank.publicinfo.mapper.MultiEntityMapper;
import com.bank.publicinfo.repository.BankDetailsRepository;
import com.bank.publicinfo.service.BankDetailsService;
import com.bank.publicinfo.util.exceptions.NotFoundException;
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
public class BankDetailsServiceImpl implements BankDetailsService {
    private final BankDetailsRepository bankDetailsRepository;
    @Override
    @Transactional
    public BankDetailsDTO createBankDetails(BankDetailsDTO bankDetailsDTO) {
        BankDetailsEntity bankDetailsEntity = MultiEntityMapper.MAPPER.toBankDetailsEntity(bankDetailsDTO);
        BankDetailsEntity saveBankDetails = bankDetailsRepository.saveAndFlush(bankDetailsEntity);
        log.info("Данные о создании деталей банка успешно добавлены в базу данных");
        return MultiEntityMapper.MAPPER.toBankDetailsDTO(saveBankDetails);
    }

    @Override
    @Transactional
    public BankDetailsDTO updateBankDetails(BankDetailsDTO bankDetailsDTO, Long id) {
        Optional<BankDetailsEntity> entityOptional = bankDetailsRepository.findById(id);
        if (entityOptional.isEmpty()) {
            log.error("Doesn't exists");
            throw new NotFoundException("No Bank Details found with given id");
        }
        BankDetailsEntity bankDetailsEntity = entityOptional.get();
        bankDetailsEntity.setBik(bankDetailsDTO.getBik());
        bankDetailsEntity.setInn(bankDetailsDTO.getInn());
        bankDetailsEntity.setKpp(bankDetailsDTO.getKpp());
        bankDetailsEntity.setCorAccount(bankDetailsDTO.getCorAccount());
        bankDetailsEntity.setCity(bankDetailsDTO.getCity());
        bankDetailsEntity.setJointStockCompany(bankDetailsDTO.getJointStockCompany());
        bankDetailsEntity.setName(bankDetailsDTO.getName());
        bankDetailsRepository.saveAndFlush(bankDetailsEntity);
        log.info("Данные деталей банка успешно обновлены в базе данных");
        return MultiEntityMapper.MAPPER.toBankDetailsDTO(bankDetailsEntity);
    }

    @Override
    @Transactional
    public void deleteBankDetails(Long id) {
        Optional<BankDetailsEntity> bankDetailsEntity = bankDetailsRepository.findById(id);
        if (bankDetailsEntity.isEmpty()) {
            log.error("Doesn't exists");
            throw new NotFoundException("No bank details with given id");
        }
        log.info("Данные успешно удалены из базы данных");
        bankDetailsRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BankDetailsDTO> getAllBankDetails() {
        if (bankDetailsRepository == null) {
            log.error("Doesn't exists");
            throw new NotFoundException("Bank Details is null");
        }
        List<BankDetailsEntity> allBankDetails = bankDetailsRepository.findAll();
        if (allBankDetails.isEmpty()) {
            log.error("Doesn't exists");
            throw new NotFoundException("No bank details found");
        }
        log.info("Все данные успешно изъяты из базы данных");
        return allBankDetails
                .stream()
                .map(MultiEntityMapper.MAPPER::toBankDetailsDTO)
                .collect(Collectors.toList());
    }
}
