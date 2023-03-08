package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.LicenseDTO;
import com.bank.publicinfo.entity.LicenseEntity;
import com.bank.publicinfo.mapper.MultiEntityMapper;
import com.bank.publicinfo.repository.BankDetailsRepository;
import com.bank.publicinfo.repository.LicenseRepository;
import com.bank.publicinfo.service.LicenseService;
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
public class LicenseServiceImpl implements LicenseService {
    private final LicenseRepository licenseRepository;
    private final BankDetailsRepository bankDetailsRepository;

    @Override
    @Transactional
    public LicenseDTO createLicense(LicenseDTO licenseDTO, Long bankId) {
        LicenseEntity licenseEntity = MultiEntityMapper.MAPPER.toLicenseEntity(licenseDTO);
        licenseEntity.setBankDetailsId(bankDetailsRepository.getReferenceById(bankId));
        LicenseEntity saveLicense = licenseRepository.save(licenseEntity);
        log.info("Данные о лицензии успешно созданы");
        return MultiEntityMapper.MAPPER.toLicenseDTO(saveLicense);
    }

    @Override
    @Transactional
    public LicenseDTO updateLicense(LicenseDTO licenseDTO, Long id) {
        Optional<LicenseEntity> entityOptional = licenseRepository.findById(id);
        if (entityOptional.isEmpty()) {
            log.error("Doesn't exists");
            throw new NotFoundException("No License found with given id");
        }
        LicenseEntity licenseEntity = entityOptional.get();
        licenseEntity.setPhoto(licenseDTO.getPhoto());
        licenseRepository.saveAndFlush(licenseEntity);
        log.info("Данные о лицензии успешно обновлены");
        return MultiEntityMapper.MAPPER.toLicenseDTO(licenseEntity);
    }

    @Override
    @Transactional
    public void deleteLicense(Long id) {
        Optional<LicenseEntity> licenseEntity = licenseRepository.findById(id);
        if (licenseEntity.isEmpty()) {
            log.error("Doesn't exists");
            throw new NotFoundException("No licenses with given id");
        }
        log.info("Данные успешно удалены из базы данных");
        licenseRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LicenseDTO> getLicenseInfo() {
        if (licenseRepository == null) {
            log.error("Doesn't exists");
            throw new NotFoundException("License is null");
        }
        List<LicenseEntity> allLicense = licenseRepository.findAll();
        if (allLicense.isEmpty()) {
            log.error("Doesn't exists");
            throw new NotFoundException("No license found");
        }
        log.info("Все данные успешно изъяты из базы данных");
        return allLicense
                .stream()
                .map(MultiEntityMapper.MAPPER::toLicenseDTO)
                .collect(Collectors.toList());
    }
}
