package com.bank.transfer.model;

import javax.persistence.*;

import com.bank.transfer.service.AuditableMy;
import com.bank.transfer.service.AuditingTransfer;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingTransfer.class)
@Table(name = "card_transfer", schema = "transfer", uniqueConstraints = {@UniqueConstraint(columnNames = {"cardNumber"}) })
public class CardTransfer extends AuditableMy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cardNumber;

    private Double amount;

    private String purpose;

    private Long accountDetailsId;
}