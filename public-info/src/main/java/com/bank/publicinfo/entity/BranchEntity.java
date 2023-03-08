package com.bank.publicinfo.entity;


import com.bank.publicinfo.service.AuditTableMy;
import com.bank.publicinfo.service.audit.AuditingBranch;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Это класс c именем BranchEntity, представляющим сущность отделения банка.
 * Класс снабжен аннотацией, @Entity чтобы пометить его
 * как объект JPA и @Table указать имя и схему таблицы базы данных.
 * <p>
 * Класс имеет следующие поля:
 * id - значение, которое является первичным ключом для объекта. Он снабжен аннотацией, @Id чтобы пометить
 * его как первичный ключ и @GeneratedValue указать, что он генерируется базой данных с использованием IDENTITY стратегии.
 * address - строковое значение, представляющее строковое представление адреса.
 * phoneNumber - поле, представляющее телефонный номер банка.
 * city - строковое значение, представляющее город, где находится отделение.
 * startOfWork - значение LocalDateTime, представляющее дату и время начала работы банкомата.
 * endOfWork - значение LocalDateTime, представляющее дату и время конца работы банкомата
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
@EntityListeners(AuditingBranch.class)
@Table(name = "branch", schema = "public_bank_information") // Отделение банкомата
public class BranchEntity extends AuditTableMy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    @Column(name = "phone_number", unique = true)
    private Long phoneNumber;
    private String city;
    @Column(name = "start_of_work")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime startOfWork;
    @Column(name = "end_of_work")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime endOfWork;
}
