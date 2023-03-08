package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.BankDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория для обработки операций с данными для сущности BankDetailsEntity.
 *
 * @author Semushkin Danila
 * @version 1.0
 * @since 15.02.2023
 */

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetailsEntity, Long> {
}
