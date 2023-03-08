package com.bank.authorization.mapper;

import com.bank.authorization.dto.AuthDto;
import com.bank.authorization.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Перевод сущности в DTO и обратно
 * @author Zhukova Ekaterina
 * @version 1.0
 * @since 15.02.2023
 */

@Mapper
public interface AutoAuthMapper {
    AutoAuthMapper MAPPER = Mappers.getMapper(AutoAuthMapper.class);

    AuthDto mapToAuthDto(Auth auth);

    Auth mapToAuth(AuthDto authDto);
}
