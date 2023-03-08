package com.bank.publicinfo.mapper;


import com.bank.publicinfo.dto.*;
import com.bank.publicinfo.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * MultiEntityMapper — это класс сопоставления, отвечающий за сопоставление между сущностями и DTO.
 * Класс использует библиотеку MapStruct для сопоставления сущностей и DTO.
 * Поля:
 * MAPPER — постоянный экземпляр MultiEntityMapper.
 *
 * @author Semushkin Danila
 * @version 1.0
 * @since 15.02.2023
 */

@Mapper(componentModel = "spring")
public interface MultiEntityMapper {
    MultiEntityMapper MAPPER = Mappers.getMapper(MultiEntityMapper.class);
    /**
     * toAtmDTO — это метод, который сопоставляет AtmEntity с AtmDTO.
     *
     * @param atmEntity сопоставляемый объект банкомата.
     * @return объект AtmDTO, представляющий сущность DTO банкомата.
     */
    AtmDTO toAtmDTO(AtmEntity atmEntity);
    /**
     * toAtmEntity — это метод, который сопоставляет AtmDTO с AtmEntity.
     *
     * @param atmDTO cопоставляемый DTO банкомата.
     * @return объект AtmEntity, представляющий сущность профиля.
     */
    AtmEntity toAtmEntity(AtmDTO atmDTO);
    /**
     * toBankDetailsDTO — это метод, который сопоставляет BankDetailsEntity с BankDetailsDTO.
     *
     * @param bankDetailsEntity сопоставляемый объект деталей банка.
     * @return объект BankDetailsDTO, представляющий сущность DTO деталей банка.
     */

    BankDetailsDTO toBankDetailsDTO(BankDetailsEntity bankDetailsEntity);

    /**
     * toBankDetailsEntity — это метод, который сопоставляет BankDetailsDTO с BankDetailsEntity.
     *
     * @param bankDetailsDTO сопоставляемый DTO деталей банка
     * @return объект BankDetailsEntity, представляющий сущность деталей банка.
     */
    BankDetailsEntity toBankDetailsEntity(BankDetailsDTO bankDetailsDTO);

    /**
     * toBranchDTO — это метод, который сопоставляет BranchEntity с BranchDTO.
     *
     * @param branchEntity сопоставляемый объект отделения банка.
     * @return объект BranchDTO, представляющий сущность DTO отделения банка.
     */

    BranchDTO toBranchDTO(BranchEntity branchEntity);

    /**
     * toBranchEntity — это метод, который сопоставляет BranchDTO с BranchEntity.
     *
     * @param branchDTO сопоставляемый DTO отделения банка
     * @return объект BranchEntity, представляющий сущность отделения банка.
     */
    BranchEntity toBranchEntity(BranchDTO branchDTO);

    /**
     * toCertificateDTO — это метод, который сопоставляет CertificateEntity с CertificateDTO.
     *
     * @param certificateEntity сопоставляемый объект сертификата банка.
     * @return объект CertificateDTO, представляющий сущность DTO сертификата банка.
     */

    CertificateDTO toCertificateDTO(CertificateEntity certificateEntity);

    /**
     * toCertificateEntity — это метод, который сопоставляет CertificateDTO с CertificateEntity.
     *
     * @param certificateDTO сопоставляемый DTO сертификата банка
     * @return объект CertificateEntity, представляющий сущность сертификата банка.
     */
    CertificateEntity toCertificateEntity(CertificateDTO certificateDTO);
    /**
     * toLicenseDTO — это метод, который сопоставляет LicenseEntity с LicenseDTO.
     *
     * @param licenseEntity сопоставляемый объект лицензии банка.
     * @return объект LicenseDTO, представляющий сущность DTO лицензии банка.
     */

    LicenseDTO toLicenseDTO(LicenseEntity licenseEntity);
    /**
     * toLicenseEntity — это метод, который сопоставляет LicenseDTO с LicenseEntity.
     *
     * @param licenseDTO сопоставляемый DTO лицензии банка
     * @return объект LicenseEntity, представляющий сущность лицензии банка.
     */
    LicenseEntity toLicenseEntity(LicenseDTO licenseDTO);
}
