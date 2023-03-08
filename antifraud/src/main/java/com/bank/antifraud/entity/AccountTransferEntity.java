package com.bank.antifraud.entity;

import com.bank.antifraud.service.audit.AuditingAccount;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author ivanvitsin
 *
 */

@Entity
@Table(name = "suspicious_account_transfers", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingAccount.class)
@Schema(description = "информация о подозрительных переводах на аккаунте")
public class AccountTransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name ="account_transfer_id", unique = true)
    private Long accountTransferId;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    @Column(name = "is_suspicious")
    private boolean isSuspicious;

    @Column(name = "blocked_reason", nullable = false)
    private String blockedReason;


    @Column(name = "suspicious_reason")
    private String suspiciousReason;



}
