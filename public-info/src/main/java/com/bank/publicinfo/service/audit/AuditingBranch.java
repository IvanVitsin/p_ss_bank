package com.bank.publicinfo.service.audit;

import com.bank.publicinfo.entity.AuditEntity;
import com.bank.publicinfo.service.AuditTableMy;
import com.bank.publicinfo.util.BeanUtil;
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
 * Этот класс представляет AuditingBranch прослушивает BranchEntity.
 * <p>
 * Обрабатывает события сохраняемости.
 * PrePersist, PostPersist, PostUpdate для  сохранения данных в таблицу аудита.
 * Логирование происходит с помощью lombok аннотацией @Slf4j с выводом в консоль.
 *
 * @author Semushkin Danila
 * @version 1.0
 * @since 15.02.2023
 */

@Slf4j
@Component
@Transactional
public class AuditingBranch {
    /**
     * Поле originalState - строковая переменная, используемая для хранения
     * исходного состояния проверяемого объекта в виде JSON формата.
     * Поле currently - временная переменная, которая фиксирует значение перед сохранением
     */
    String originalState;
    LocalDateTime currently;

    /**
     * Данный метод выполняется перед сохранением.
     * Устанавливает исходное состояние проверяемого объекта в строку JSON.
     *
     * @param auditable - сохраняемый проверяемый объект.
     */

    @PrePersist
    public void prePersist(AuditTableMy auditable) {
        this.originalState = toJson(auditable);
        this.currently = LocalDateTime.now();
        log.info("Entity data before saving added to fields.");
    }

    /**
     * Данный метод выполняется после prePersist.
     * Создает объект аудита и устанавливает его атрибуты на основе сохраняемого объекта аудита.
     * После созданный объект аудита сохраняется в БД.
     *
     * @param auditable - сохраненный проверяемый объект.
     */

    @PostPersist
    public void postPersist(AuditTableMy auditable) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setEntityType(auditable.getClass().getSimpleName());
        auditEntity.setOperationType("Persist");
        auditEntity.setCreatedBy("User");
        auditEntity.setCreatedAt(LocalDateTime.now());
        auditEntity.setModifiedBy("User");
        auditEntity.setModifiedAt(LocalDateTime.now());
        auditEntity.setEntityJson(toJson(auditable));
        auditEntity.setNewEntityJson(toJson(auditable));
        entityManager.persist(auditEntity);
        log.info("Entity data added to Audit Table");
    }

    /**
     * Метод, выполняется после события обновления.
     * Создает объект аудита и устанавливает его атрибуты на основе объекта, который был обновлен.
     * Созданный объект аудита сохраняется в БД.
     *
     * @param auditable - объект аудита, который был обновлен.
     */

    @PostUpdate
    public void postUpdate(AuditTableMy auditable) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setEntityType(auditable.getClass().getSimpleName());
        auditEntity.setOperationType("Update");
        auditEntity.setCreatedBy("User");
        auditEntity.setModifiedBy("User");
        auditEntity.setCreatedAt(currently);
        auditEntity.setModifiedAt(LocalDateTime.now());
        auditEntity.setEntityJson(originalState);
        auditEntity.setNewEntityJson(toJson(auditable));
        entityManager.persist(auditEntity);
        log.info("Entity data updated in Audit Table");
    }

    /**
     * Метод преобразует объект в формат JSON
     *
     * @param object - объект, который необходимо преобразовать в JSON.
     * @return
     */

    private String toJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            log.info("Entity data converted to json");
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("ERR: Entity cannot converted to json");
            throw new RuntimeException(e);
        }
    }
}
