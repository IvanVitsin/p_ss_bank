package com.bank.publicinfo.entity;

import com.bank.publicinfo.service.AuditTableMy;
import com.bank.publicinfo.service.audit.AuditingAtm;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Это класс c именем AtmEntity, представляющим сущность банкомата.
 * Класс снабжен аннотацией, @Entity чтобы пометить его
 * как объект JPA и @Table указать имя и схему таблицы базы данных.
 * <p>
 * Класс имеет следующие поля:
 * id - значение, которое является первичным ключом для объекта. Он снабжен аннотацией, @Id чтобы пометить
 * его как первичный ключ и @GeneratedValue указать, что он генерируется базой данных с использованием IDENTITY стратегии.
 * address - строковое значение, представляющее строковое представление адреса.
 * startOfWork - значение LocalDateTime, представляющее дату и время начала работы банкомата.
 * endOfWork - значение LocalDateTime, представляющее дату и время конца работы банкомата
 * allHours - булевское значение, представляющее значение работы банкомата 24/7 (true/false)
 * branchId - BranchEntity поле с аннотацией представляющее связанный объект отделения.
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
@EntityListeners(AuditingAtm.class)
@Table(name = "atm", schema = "public_bank_information") // Сущность банкомата
public class AtmEntity extends AuditTableMy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    @Column(name = "start_of_work")
    private LocalDateTime startOfWork;
    @Column(name = "end_of_work")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime endOfWork;
    @Column(name = "all_hours")
    private boolean allHours;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private BranchEntity branchId;

}
