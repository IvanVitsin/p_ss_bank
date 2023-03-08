package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.CertificateDTO;
import com.bank.publicinfo.entity.BankDetailsEntity;
import com.bank.publicinfo.entity.CertificateEntity;
import com.bank.publicinfo.mapper.MultiEntityMapper;
import com.bank.publicinfo.repository.BankDetailsRepository;
import com.bank.publicinfo.repository.CertificateRepository;
import com.bank.publicinfo.service.CertificateService;
import com.bank.publicinfo.util.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certificateRepository;
    private final BankDetailsRepository bankDetailsRepository;

    @Override
    @Transactional
    public CertificateDTO createCertificate(CertificateDTO certificateDTO, Long bankId) {
        CertificateEntity certificateEntity = MultiEntityMapper.MAPPER.toCertificateEntity(certificateDTO);
        certificateEntity.setBankDetailsId(bankDetailsRepository.getReferenceById(bankId));
        CertificateEntity saveCertificate = certificateRepository.save(certificateEntity);
        log.info("Данные о сертификате успешно созданы");
        return MultiEntityMapper.MAPPER.toCertificateDTO(saveCertificate);
    }

    @Override
    @Transactional
    public CertificateDTO updateCertificate(CertificateDTO certificateDTO, Long id) {
        Optional<CertificateEntity> entityOptional = certificateRepository.findById(id);
        if (entityOptional.isEmpty()) {
            log.error("Doesn't exists");
            throw new NotFoundException("No Certificates found with given id");
        }
        CertificateEntity certificateEntity = entityOptional.get();
        certificateEntity.setPhoto(certificateDTO.getPhoto());
        certificateRepository.saveAndFlush(certificateEntity);
        log.info("Данные о сертификате успешно обновлены");
        return MultiEntityMapper.MAPPER.toCertificateDTO(certificateEntity);
    }

    @Override
    @Transactional
    public void deleteCertificate(Long id) {
        Optional<CertificateEntity> certificateEntity = certificateRepository.findById(id);
        if (certificateEntity.isEmpty()) {
            log.error("Doesn't exists");
            throw new NotFoundException("No certificates with given id");
        }
        log.info("Данные успешно удалены из базы данных");
        certificateRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CertificateDTO> getCertificateInfo() {
        if (certificateRepository == null) {
            log.error("Doesn't exists");
            throw new NotFoundException("Certificate is null");
        }
        List<CertificateEntity> allCertificate = certificateRepository.findAll();
        if (allCertificate.isEmpty()) {
            log.error("Doesn't exists");
            throw new NotFoundException("No certificate found");
        }
        log.info("Все данные успешно изъяты из базы данных");
        return allCertificate
                .stream()
                .map(MultiEntityMapper.MAPPER::toCertificateDTO)
                .collect(Collectors.toList());
    }
}
