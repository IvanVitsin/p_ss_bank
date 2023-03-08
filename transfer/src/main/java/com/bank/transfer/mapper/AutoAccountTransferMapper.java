package com.bank.transfer.mapper;


import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.model.AccountTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AutoAccountTransferMapper {
    AutoAccountTransferMapper ACCOUNT_TRANSFER_MAPPER = Mappers.getMapper(AutoAccountTransferMapper.class);

    AccountTransferDto mapToAccountTransferDto(AccountTransfer accountTransfer);

    AccountTransfer mapToAccountTransfer(AccountTransferDto accountTransfer);

    List<AccountTransferDto> mapToAllAccountTransferDtos(List<AccountTransfer> accountTransfers);

    List<AccountTransfer> mapToAllAccountTransfers(List<AccountTransferDto> accountTransferDtos);

}
