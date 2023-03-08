package com.bank.transfer.service;

import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.exception.NotFoundException;
import com.bank.transfer.mapper.AutoAccountTransferMapper;
import com.bank.transfer.model.AccountTransfer;
import com.bank.transfer.repository.AccountTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountTransferServiceImpl implements AccountTransferService {

    private final AccountTransferRepository accountTransferRepository;


    @Override
    @Transactional
    public void save(@Valid AccountTransferDto accountTransferDto) {
        accountTransferRepository.save(AutoAccountTransferMapper.ACCOUNT_TRANSFER_MAPPER.mapToAccountTransfer(accountTransferDto));
    }

    @Override
    @Transactional
    public void delete(Long accountDetailsId) {
        accountTransferRepository.deleteAccountTransferByAccountDetailsId(accountDetailsId);
    }

    @Override
    public AccountTransferDto findByAccountNumber(Long accountNumber) {
        Optional<AccountTransfer> accountTransfer = accountTransferRepository.findByAccountNumber(accountNumber);
        if (accountTransfer.isEmpty()) {
            throw new NotFoundException("Перевод по этому номеру счета: " + accountNumber+ " не найден");
        }
        return AutoAccountTransferMapper.ACCOUNT_TRANSFER_MAPPER.mapToAccountTransferDto(accountTransfer.get());
    }

    @Override
    public List<AccountTransferDto> findAll() {
        return AutoAccountTransferMapper.ACCOUNT_TRANSFER_MAPPER.mapToAllAccountTransferDtos(accountTransferRepository.findAll());
    }
}

