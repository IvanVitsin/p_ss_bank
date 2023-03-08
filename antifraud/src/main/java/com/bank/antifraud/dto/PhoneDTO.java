package com.bank.antifraud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Класс PhoneDTO — это объект передачи данных (DTO),
 * представляющий детали По транзакциям по номеру телефона.
 *
 * @author ivan vitsin
 * @version 1.0
 * @since 20.02.2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PhoneDTO {
    @Schema(description = "Первичный ключ")
    @NotNull
    private Long id;
    @NotNull
    @Schema(description = "Идентификатор перевода по номеру телефона")
    private Long phoneTransferId;
    @NotNull
    @Schema(description = "заблокирован/не заблокирован")
    private boolean isBlocked;
    @NotNull
    @Schema(description = "подозрительный/не подозрительный")
    private boolean isSuspicious;

    @Schema(description = "причина блокировки")
    private String blockedReason;

    @Schema(description = "причина подозрений")
    private String suspiciousReason;
}
