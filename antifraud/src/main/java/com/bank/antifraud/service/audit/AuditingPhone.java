package com.bank.antifraud.service.audit;

import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.util.BeanInit;
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

@Slf4j
@Component
@Transactional
public class AuditingPhone {


    String originalState;
    LocalDateTime now;

    /**
     * Этот метод выполняется перед событием сохранения.
     * Он устанавливает исходное состояние проверяемого объекта в строку JSON.
     *
     * @param auditAbstract — сохраняемый проверяемый объект.
     */
    @PrePersist
    public void prePersist(AuditAbstract auditAbstract) {
        this.originalState = toJson(auditAbstract);
        this.now = LocalDateTime.now();
        log.info("Данные о entity перед сохранением добавлены в поля");
    }

    /**
     * Этот метод выполняется после события prePersist.
     * Он создает объект аудита и устанавливает его атрибуты на основе сохраняемого объекта аудита.
     * Созданный объект аудита затем сохраняется в базе данных.
     *
     * @param auditAbstract — сохраненный проверяемый объект.
     */
    @PostPersist
    public void postPersist(AuditAbstract auditAbstract) {
        EntityManager entityManager = BeanInit.getBean(EntityManager.class);
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setEntityType(auditAbstract.getClass().getSimpleName());
        auditEntity.setOperationType("Persist");
        auditEntity.setCreatedBy("User");
        auditEntity.setCreatedAt(LocalDateTime.now());
        auditEntity.setModifiedBy("User");
        auditEntity.setModifiedAt(LocalDateTime.now());
        auditEntity.setEntityJson(toJson(auditAbstract));
        auditEntity.setNewEntityJson(toJson(auditAbstract));
        entityManager.persist(auditEntity);
        log.info("Данные об entity добавлены в таблицу аудит");
    }

    /**
     * Этот метод выполняется после события обновления.
     * Он создает объект аудита и устанавливает его атрибуты на основе объекта аудита, который был обновлен.
     * Созданный объект аудита затем сохраняется в базе данных.
     *
     * @param auditAbstract — объект аудита, который был обновлен.
     */
    @PostUpdate
    public void postUpdate(AuditAbstract auditAbstract) {
        EntityManager entityManager = BeanInit.getBean(EntityManager.class);
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setEntityType(auditAbstract.getClass().getSimpleName());
        auditEntity.setOperationType("Update");
        auditEntity.setCreatedBy("User");
        auditEntity.setModifiedBy("User");
        auditEntity.setCreatedAt(now);
        auditEntity.setModifiedAt(LocalDateTime.now());
        auditEntity.setEntityJson(originalState);
        auditEntity.setNewEntityJson(toJson(auditAbstract));
        entityManager.persist(auditEntity);
        log.info("Данные о entity обновлены в таблице аудит");
    }

    /**
     * Этот метод преобразует объект в формат JSON.
     *
     * @param object — объект, который нужно преобразовать в формат JSON.
     */
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
