package com.bank.transfer.service;

import com.bank.transfer.dto.PhoneTransferDto;
import java.util.List;

public interface PhoneTransferService {

    void save(PhoneTransferDto phoneTransferDto);

    void delete(Long accountDetailsId);

    PhoneTransferDto findByPhoneNumber(Long phoneNumber);

    List<PhoneTransferDto> findAll();
}
