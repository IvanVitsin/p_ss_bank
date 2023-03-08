package com.bank.publicinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Класс LicenseDTO — это объект передачи данных (DTO),
 * представляющий информацию о лицензиях банка.
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
@Schema(description = "Информация о лицензиях банка")
public class LicenseDTO implements Serializable {
    @Schema(description = "Фотография лицензии")
    @NotNull
    private byte[] photo;
}
