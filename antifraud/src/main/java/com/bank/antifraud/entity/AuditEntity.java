package com.bank.antifraud.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/*Сущность связанная с таблицей в бд */


@Entity
@Table(name = "audit", schema = "anti_fraud")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "аудит для сущностей по переводам")
public class AuditEntity {
    @Schema(description = "идентификатор ")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entityType;

    private String operationType;

    private String createdBy;

    @Column(nullable = false)
    private String modifiedBy;

    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime modifiedAt;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String newEntityJson;

    @Column(columnDefinition = "TEXT")
    private String entityJson;
}
