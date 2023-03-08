package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.entity.AtmEntity;
import com.bank.publicinfo.entity.BranchEntity;
import com.bank.publicinfo.repository.AtmRepository;
import com.bank.publicinfo.repository.BranchRepository;
import com.bank.publicinfo.service.AtmService;
import com.bank.publicinfo.mapper.MultiEntityMapper;
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
public class AtmServiceImpl implements AtmService {

    private final AtmRepository atmRepository;
    private final BranchRepository branchRepository;


    @Override
    @Transactional(readOnly = true)
    public List<AtmDTO> getAllAtmInfo() {
        if (atmRepository == null) {
            log.error("Doesn't exist");
            throw new NotFoundException("atmRepository is null");
        }
        List<AtmEntity> allAtm = atmRepository.findAll();
        if (allAtm.isEmpty()) {
            log.error("Doesn't exists");
            throw new NotFoundException("Atm not found");
        }
        log.info("Данные изъяты из базы данных");
        return allAtm
                .stream()
                .map(MultiEntityMapper.MAPPER::toAtmDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteAtm(Long id) {
        Optional<AtmEntity> atmEntity = atmRepository.findById(id);
        if (atmEntity.isEmpty()) {
            log.error("Doesn't exists");
            throw new NotFoundException("No Atm found by id");
        }
        log.info("Данные успешно удалены из базы данных");
        atmRepository.deleteById(id);
    }

    @Override
    @Transactional
    public AtmDTO updateAtm(AtmDTO atmDTO, Long id) {
        Optional<AtmEntity> entityOptional = atmRepository.findById(id);
        if (entityOptional.isEmpty()) {
            log.error("Doesn't exists");
            throw new NotFoundException("No atm found by id");
        }
        AtmEntity atmEntity = entityOptional.get();
        atmEntity.setAddress(atmDTO.getAddress());
        atmEntity.setStartOfWork(atmDTO.getStartOfWork());
        atmEntity.setEndOfWork(atmDTO.getEndOfWork());
        atmRepository.saveAndFlush(atmEntity);
        log.info("Данные о регистрации успешно обновлены в базе данных");
        return MultiEntityMapper.MAPPER.toAtmDTO(atmEntity);
    }

    @Override
    @Transactional
    public AtmDTO createAtm(AtmDTO atmDTO, Long branchId) {
        Optional<BranchEntity> entityOptional = branchRepository.findById(branchId);
        if (entityOptional.isEmpty()) {
            log.error("Doesn't existst");
            throw new NotFoundException("No atm id");
        }
        AtmEntity atmEntity = MultiEntityMapper.MAPPER.toAtmEntity(atmDTO);
        atmEntity.setBranchId(branchRepository.getReferenceById(branchId));
        AtmEntity saveAtm = atmRepository.saveAndFlush(atmEntity);
        log.info("Данные о банкомате добавлены в базу данных");
        return MultiEntityMapper.MAPPER.toAtmDTO(saveAtm);
    }


}
