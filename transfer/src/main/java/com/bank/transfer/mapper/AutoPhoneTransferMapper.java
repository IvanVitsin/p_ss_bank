package com.bank.transfer.mapper;


import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.model.PhoneTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AutoPhoneTransferMapper {
    AutoPhoneTransferMapper PHONE_TRANSFER_MAPPER = Mappers.getMapper(AutoPhoneTransferMapper.class);

    PhoneTransferDto mapToPhoneTransferDto(PhoneTransfer phoneTransfer);

    PhoneTransfer mapToPhoneTransfer(PhoneTransferDto phoneTransfer);

    List<PhoneTransferDto> mapToAllPhoneTransferDtos(List<PhoneTransfer> phoneTransfers);

    List<PhoneTransfer> mapToAllPhoneTransfers(List<PhoneTransferDto> phoneTransferDtos);

}
