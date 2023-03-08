package com.bank.authorization.repository;

import com.bank.authorization.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Обработка операций с данными для сущности Auth
 * @author Zhukova Ekaterina
 * @version 1.0
 * @since 15.02.2023
 */

public interface AuthRepository extends JpaRepository<Auth, Long> {
}
