package com.bank.authorization.service;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 * Базовый класс для аудита сущностей
 * @author Zhukova Ekaterina
 * @version 1.0
 * @since 15.02.2023
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
}
