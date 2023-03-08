package com.bank.publicinfo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Это класс c именем AuditEntity, представляющим сущность аудита
 * в таблице базы данных audit с именем схемы profile.
 * Класс снабжен аннотацией, @Entity чтобы пометить его
 * как объект JPA и @Table указать имя и схему таблицы базы данных.
 * <p>
 * Класс имеет следующие поля:
 * <p>
 * id - значение, которое является первичным ключом для объекта. Он снабжен аннотацией, @Id чтобы пометить
 * его как первичный ключ и @GeneratedValue указать, что он генерируется базой данных с использованием IDENTITY стратегии.
 * <p>
 * entityType - строковое значение, представляющее тип проверенного объекта.
 * <p>
 * operationType - строковое значение, представляющее тип операции, выполненной над объектом,
 * <p>
 * createdBy - строковое значение, представляющее имя пользователя, создавшего объект.
 * <p>
 * modifiedBy - строковое значение, представляющее имя пользователя, который  изменил объект.
 * <p>
 * createdAt - значение LocalDateTime, представляющее дату и время создания объекта.
 * <p>
 * modifiedAt - значение LocalDateTime, представляющее дату и время последнего изменения объекта.
 * <p>
 * newEntityJson - строковое значение, представляющее данные JSON при создании сущности.
 * <p>
 * entityJson - строковое значение, представляющее данные JSON после изменения объекта.
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
@Table(name = "audit", schema = "public_bank_information")
public class AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "entity_type")
    private String entityType;
    @Column(name = "operation_type")
    private String operationType;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "modified_by", nullable = false)
    private String modifiedBy;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;
    @Column(name = "new_entity_json", nullable = false, columnDefinition = "TEXT")
    private String newEntityJson;
    @Column(name = "entity_json", columnDefinition = "TEXT")
    private String entityJson;
}
