package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.AccountDTO;
import com.bank.antifraud.dto.CardDTO;
import com.bank.antifraud.dto.PhoneDTO;
import com.bank.antifraud.entity.AccountTransferEntity;
import com.bank.antifraud.entity.CardTransferEntity;
import com.bank.antifraud.entity.PhoneTransferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
/**
 * интерфейс AllEntityMapper — общий интерфейс для всех сущностей текущего модуля,
 * методы которого преобразуют обьект БД в обьекты DTO.
 *
 * @author ivan vitsin
 * @version 1.0
 * @since 20.02.2023
 */
@Mapper(componentModel = "spring")
public interface AllEntityMapper {
    AllEntityMapper MAPPER = Mappers.getMapper(AllEntityMapper.class);


    AccountDTO toDTO(AccountTransferEntity accountTransferEntity);
    AccountTransferEntity toEntity(AccountDTO accountDTO);


    CardDTO toDTO(CardTransferEntity cardTransferEntity);
    CardTransferEntity toEntity(CardDTO cardDTO);

    PhoneDTO toDTO(PhoneTransferEntity phoneTransferEntity);
    PhoneTransferEntity toEntity(PhoneDTO phoneDTO);
}
