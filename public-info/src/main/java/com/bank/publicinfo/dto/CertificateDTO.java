package com.bank.publicinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Класс CertificateDTO — это объект передачи данных (DTO),
 * представляющий информацию о сертификатах банка.
 *
 * @author Semushkin Danila
 * @version 1.0
 * @since 15.02.2023
 */

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация о сертификатах банка")
public class CertificateDTO implements Serializable {
    @Schema(description = "Фотография сертификата")
    @NotNull
    private byte[] photo;
}
