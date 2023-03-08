package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BankDetailsDTO;

import java.util.List;

public interface BankDetailsService {
    BankDetailsDTO createBankDetails(BankDetailsDTO bankDetailsDTO);
    BankDetailsDTO updateBankDetails(BankDetailsDTO bankDetailsDTO, Long id);
    void deleteBankDetails(Long id);
    List<BankDetailsDTO> getAllBankDetails();
}
