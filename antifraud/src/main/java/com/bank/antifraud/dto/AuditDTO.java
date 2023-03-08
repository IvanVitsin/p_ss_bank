package com.bank.antifraud.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * Класс AuditDTO — это объект передачи данных (DTO),
 * представляющий сущность, которая собирает данные обо всех подозрительных транзакциях
 *  записывает дату и время создания/измнения транзакций.
 *
 * @author ivan vitsin
 * @version 1.0
 * @since 20.02.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditDTO implements Serializable {
    @NotNull
    private Long id;
    @NotNull
    private String entityType;
    @NotNull
    private String operationType;
    @NotNull
    private String createdBy;
    private String modifiedBy;
    @NotNull
    private OffsetDateTime createdAt;
    private OffsetDateTime modifiedAt;
    private String newEntityJson;
    @NotNull
    private String entityJson;
}
