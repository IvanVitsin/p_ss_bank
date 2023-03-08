package com.bank.publicinfo.service;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 * Абстрактный класс для аудита
 * Этот класс служит базовым классом для аудита сущностей в приложении.
 * Он использует аннотацию JPA @MappedSuperclass, чтобы указать, что он является суперклассом для других сущностей.
 * Класс снабжен аннотацией @EntityListeners и связанным с ним классом прослушивателя AuditingEntityListener.
 *
 * @author Semushkin Danila
 * @version 1.0
 * @since 15.02.2023
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditTableMy {
}
