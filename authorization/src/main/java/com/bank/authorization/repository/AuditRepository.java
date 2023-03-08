package com.bank.authorization.repository;

import com.bank.authorization.entity.AuthAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Обработка операций с данными для сущности AuthAudit
 * @author Zhukova Ekaterina
 * @version 1.0
 * @since 15.02.2023
 */

@Repository
public interface AuditRepository extends JpaRepository<AuthAudit, Long> {
}
