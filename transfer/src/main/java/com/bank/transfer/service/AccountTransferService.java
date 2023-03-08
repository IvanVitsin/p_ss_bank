package com.bank.transfer.service;

import com.bank.transfer.dto.AccountTransferDto;
import java.util.List;

public interface AccountTransferService {

    void save(AccountTransferDto accountTransferDto);

    void delete(Long accountDetailsId);

    AccountTransferDto findByAccountNumber(Long accountNumber);

    List<AccountTransferDto> findAll();
}
