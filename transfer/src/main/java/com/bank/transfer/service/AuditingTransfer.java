package com.bank.transfer.service;

import com.bank.transfer.model.AuditEntity;
import com.bank.transfer.util.BeanUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;


@Component
@Transactional
public class AuditingTransfer {
    String originalState;
    LocalDateTime now;

    @PrePersist
    public void prePersist(AuditableMy auditable) {
        this.originalState = toJson(auditable);
        this.now = LocalDateTime.now();
    }

    @PostPersist
    public void postPersist(AuditableMy auditable) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setEntityType(auditable.getClass().getSimpleName());
        auditEntity.setOperationType("Persist");
        auditEntity.setCreatedBy("Mihail");
        auditEntity.setCreatedAt(LocalDateTime.now());
        auditEntity.setModifiedBy("Mihail");
        auditEntity.setModifiedAt(LocalDateTime.now());
        auditEntity.setEntityJson(toJson(auditable));
        auditEntity.setNewEntityJson(toJson(auditable));
        entityManager.persist(auditEntity);
    }

    private String toJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}


