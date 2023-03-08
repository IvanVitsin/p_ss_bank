package com.bank.transfer.model;

import com.bank.transfer.service.AuditableMy;
import com.bank.transfer.service.AuditingTransfer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingTransfer.class)
@Table(name = "phone_transfer", schema = "transfer")
public class PhoneTransfer extends AuditableMy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long phoneNumber;

    private Double amount;

    private String purpose;

    private Long accountDetailsId;
}