package com.bank.authorization.entity;

import com.bank.authorization.service.Auditable;
import com.bank.authorization.service.AuditingAuth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность, которая соответствует таблице users
 * @author Zhukova Ekaterina
 * @version 1.0
 * @since 15.02.2023
 */

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingAuth.class)
public class Auth extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "profile_id", nullable = false)
    private long profile_id;

    @Column(name = "password", nullable = false, unique = true)
    private String password;
}
