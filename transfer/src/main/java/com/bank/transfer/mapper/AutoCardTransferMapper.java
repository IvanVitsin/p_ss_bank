package com.bank.transfer.mapper;

import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.model.CardTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AutoCardTransferMapper {
    AutoCardTransferMapper CARD_TRANSFER_MAPPER = Mappers.getMapper(AutoCardTransferMapper.class);

    CardTransferDto mapToCardTransferDto(CardTransfer cardTransfer);

    CardTransfer mapToCardTransfer(CardTransferDto cardTransferDto);

    List<CardTransferDto> mapToAllCardTransferDtos(List<CardTransfer> cardTransfers);

    List<CardTransfer> mapToAllCardTransfers(List<CardTransferDto> cardTransferDtos);

}
