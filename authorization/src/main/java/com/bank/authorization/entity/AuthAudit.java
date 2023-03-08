package com.bank.authorization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Сущность, которая соответствует таблице audit
 * @author Zhukova Ekaterina
 * @version 1.0
 * @since 15.02.2023
 */

@Entity
@Table(name = "audit")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthAudit {
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
