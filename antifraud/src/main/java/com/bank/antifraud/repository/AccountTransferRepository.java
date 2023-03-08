package com.bank.antifraud.repository;

import com.bank.antifraud.entity.AccountTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransferRepository extends JpaRepository<AccountTransferEntity, Long> {

}
