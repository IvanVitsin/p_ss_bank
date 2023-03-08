package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.dto.BranchDTO;
import com.bank.publicinfo.entity.BranchEntity;
import com.bank.publicinfo.mapper.MultiEntityMapper;
import com.bank.publicinfo.repository.BranchRepository;
import com.bank.publicinfo.service.BranchService;
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
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    @Override
    @Transactional
    public BranchDTO createBranch(BranchDTO branchDTO) {
        BranchEntity branchEntity = MultiEntityMapper.MAPPER.toBranchEntity(branchDTO);
        BranchEntity saveBranch = branchRepository.saveAndFlush(branchEntity);
        log.info("Данные об отделении созданы");
        return MultiEntityMapper.MAPPER.toBranchDTO(saveBranch);
    }

    @Override
    @Transactional
    public BranchDTO updateBranch(BranchDTO branchDTO, Long id) {
        Optional<BranchEntity> entityOptional = branchRepository.findById(id);
        if (entityOptional.isEmpty()) {
            log.error("Doesn't exists");
            throw new NotFoundException("No branch with given id");
        }
        BranchEntity branchEntity = entityOptional.get();
        branchEntity.setId(id);
        branchEntity.setCity(branchDTO.getCity());
        branchEntity.setAddress(branchDTO.getAddress());
        branchEntity.setPhoneNumber(branchDTO.getPhoneNumber());
        branchEntity.setStartOfWork(branchDTO.getStartOfWork());
        branchEntity.setEndOfWork(branchDTO.getEndOfWork());
        branchRepository.saveAndFlush(branchEntity);
        log.info("Данные об отделении были обновлены");
        return MultiEntityMapper.MAPPER.toBranchDTO(branchEntity);
    }

    @Override
    @Transactional
    public void deleteBranch(Long id) {
        Optional<BranchEntity> branchEntity = branchRepository.findById(id);
        if (branchEntity.isEmpty()) {
            log.error("does not exist");
            throw new NotFoundException("No branch found with the given id");
        }
        branchRepository.deleteById(id);
        log.info("Данные об отделении удалены из базы данных");
    }

    @Override
    @Transactional(readOnly = true)
    public List<BranchDTO> getBranch() {
        if (branchRepository == null) {
            throw new NotFoundException("branch repository is null");
        }
        List<BranchEntity> allBranches = branchRepository.findAll();
        if (allBranches.isEmpty()) {
            log.error("does not exist");
            throw new NotFoundException("No branches found");
        }
        log.info("Данные об отделении изьяты из базы данных");
        return allBranches
                .stream()
                .map(MultiEntityMapper.MAPPER::toBranchDTO)
                .collect(Collectors.toList());
    }
}
