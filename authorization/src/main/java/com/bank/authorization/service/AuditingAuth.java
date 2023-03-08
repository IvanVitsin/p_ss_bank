package com.bank.authorization.service;

import com.bank.authorization.entity.AuthAudit;
import com.bank.authorization.util.BeanUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

/**
 * Класс, который прослушивает изменения в Auth
 * @author Zhukova Ekaterina
 * @version 1.0
 * @since 15.02.2023
 */

@Slf4j
@Component
@Transactional
public class AuditingAuth {

    String originalState;

    LocalDateTime now;

    @PrePersist
    public void prePersist(Auditable auditable) {
        this.originalState = toJson(auditable);
        this.now = LocalDateTime.now();
        log.info("Данные о entity перед сохранением добавлены в поля");
    }

    @PostPersist
    public void postPersist(Auditable auditable) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        AuthAudit auditEntity = new AuthAudit();
        auditEntity.setEntityType(auditable.getClass().getSimpleName());
        auditEntity.setOperationType("Persist");
        auditEntity.setCreatedBy("User");
        auditEntity.setCreatedAt(LocalDateTime.now());
        auditEntity.setModifiedBy("User");
        auditEntity.setModifiedAt(LocalDateTime.now());
        auditEntity.setEntityJson(toJson(auditable));
        auditEntity.setNewEntityJson(toJson(auditable));
        entityManager.persist(auditEntity);
        log.info("Данные о entity добавлены в таблицу аудит");
    }

    @PostUpdate
    public void postUpdate(Auditable auditable) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        AuthAudit auditEntity = new AuthAudit();
        auditEntity.setEntityType(auditable.getClass().getSimpleName());
        auditEntity.setOperationType("Update");
        auditEntity.setCreatedBy("User");
        auditEntity.setModifiedBy("User");
        auditEntity.setCreatedAt(now);
        auditEntity.setModifiedAt(LocalDateTime.now());
        auditEntity.setEntityJson(originalState);
        auditEntity.setNewEntityJson(toJson(auditable));
        entityManager.persist(auditEntity);
        log.info("Данные о entity обновлены в таблице аудит");
    }

    private String toJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            log.info("Данные о entity преобразованы в json");
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("ERROR: entity не преобразован в json");
            throw new RuntimeException(e);
        }
    }

}


