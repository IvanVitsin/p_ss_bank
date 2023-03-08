package com.bank.publicinfo.entity;

import com.bank.publicinfo.service.AuditTableMy;
import com.bank.publicinfo.service.audit.AuditingLicense;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Это класс c именем LicenseEntity, представляющим сущность лицензии банка.
 * Класс снабжен аннотацией, @Entity чтобы пометить его
 * как объект JPA и @Table указать имя и схему таблицы базы данных.
 * <p>
 * Класс имеет следующие поля:
 * id - значение, которое является первичным ключом для объекта. Он снабжен аннотацией, @Id чтобы пометить
 * его как первичный ключ и @GeneratedValue указать, что он генерируется базой данных с использованием IDENTITY стратегии.
 * photo - массив байтов, представляющий фото лицензии банка.
 * bankDetailsId - BankDetailsEntity поле, представляющее связанные детали банка объекта.
 *
 * @author Semushkin Danila
 * @version 1.0
 * @since 15.02.2023
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingLicense.class)
@Table(name = "license", schema = "public_bank_information")
public class LicenseEntity extends AuditTableMy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte[] photo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "bank_details_id", referencedColumnName = "id")
    private BankDetailsEntity bankDetailsId;
}
