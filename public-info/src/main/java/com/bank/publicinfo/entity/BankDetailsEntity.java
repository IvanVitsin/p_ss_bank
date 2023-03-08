package com.bank.publicinfo.entity;


import com.bank.publicinfo.service.AuditTableMy;
import com.bank.publicinfo.service.audit.AuditingBankDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Это класс c именем BankDetailsEntity, представляющим сущность деталей банка.
 * Класс снабжен аннотацией, @Entity чтобы пометить его
 * как объект JPA и @Table указать имя и схему таблицы базы данных.
 * <p>
 * Класс имеет следующие поля:
 * id - значение, которое является первичным ключом для объекта. Он снабжен аннотацией, @Id чтобы пометить
 * его как первичный ключ и @GeneratedValue указать, что он генерируется базой данных с использованием IDENTITY стратегии.
 * bik - поле, представляющее банковский идентификационный код.
 * inn - поле, представляющее индивидуальный налоговый номер банка.
 * kpp - поле, представляющее код причины постановки клиента.
 * corAccount - поле, представляющее корреспондентский счет.
 * city - строковое значение, представляющее город, в котором находится банк.
 * jointStockCompany - строковое значение, представляющее юр.лицо банка.
 * name - строковое значение, представляющее название банка.
 *
 * @author Semushkin Danila
 * @version 1.0
 * @since 15.02.2023
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingBankDetails.class)
@Table(name = "bank_details", schema = "public_bank_information")
public class BankDetailsEntity extends AuditTableMy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bik", unique = true)
    private Long bik;
    @Column(name = "inn", unique = true)
    private Long inn;
    @Column(name = "kpp", unique = true)
    private Long  kpp;
    @Column(name = "cor_account", unique = true)
    private Long corAccount;
    private String city;
    @Column(name = "joint_stock_company")
    private String jointStockCompany;
    private String name;
}
