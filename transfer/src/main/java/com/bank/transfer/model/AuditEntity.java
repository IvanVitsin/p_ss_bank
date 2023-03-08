package com.bank.transfer.model;

import com.bank.transfer.service.AuditingTransfer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audit", schema = "transfer")
public class AuditEntity {
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
