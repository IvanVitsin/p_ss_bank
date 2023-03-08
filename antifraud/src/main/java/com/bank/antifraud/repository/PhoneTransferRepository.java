package com.bank.antifraud.repository;

import com.bank.antifraud.entity.PhoneTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PhoneTransferRepository extends JpaRepository<PhoneTransferEntity, Long> {
}
