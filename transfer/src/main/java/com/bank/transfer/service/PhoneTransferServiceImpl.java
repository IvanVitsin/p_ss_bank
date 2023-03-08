package com.bank.transfer.service;

import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.exception.NotFoundException;
import com.bank.transfer.mapper.AutoPhoneTransferMapper;
import com.bank.transfer.model.PhoneTransfer;
import com.bank.transfer.repository.PhoneTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhoneTransferServiceImpl implements PhoneTransferService {

    private final PhoneTransferRepository phoneTransferRepository;


    @Override
    @Transactional
    public void save(@Valid PhoneTransferDto phoneTransferDto) {
        phoneTransferRepository.save(AutoPhoneTransferMapper.PHONE_TRANSFER_MAPPER.mapToPhoneTransfer(phoneTransferDto));
    }

    @Override
    @Transactional
    public void delete(Long accountDetailsId) {
        phoneTransferRepository.deletePhoneTransferByAccountDetailsId(accountDetailsId);
    }

    @Override
    public PhoneTransferDto findByPhoneNumber(Long phoneNumber) {
        Optional<PhoneTransfer> phoneTransfer = phoneTransferRepository.findByPhoneNumber(phoneNumber);
        if (phoneTransfer.isEmpty()) {
            throw new NotFoundException("Перевод по этому номеру телефона: " + phoneNumber+ " не найден");
        }
        return AutoPhoneTransferMapper.PHONE_TRANSFER_MAPPER.mapToPhoneTransferDto(phoneTransfer.get());
    }

    @Override
    public List<PhoneTransferDto> findAll() {
        return AutoPhoneTransferMapper.PHONE_TRANSFER_MAPPER.mapToAllPhoneTransferDtos(phoneTransferRepository.findAll());
    }
}

