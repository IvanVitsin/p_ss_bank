package com.bank.antifraud.repository;

import com.bank.antifraud.entity.CardTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CardTransferRepository extends JpaRepository<CardTransferEntity, Long> {
}
