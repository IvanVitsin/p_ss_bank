package com.bank.publicinfo.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Класс AtmDTO — это объект передачи данных (DTO),
 * представляющий информацию о банкомате.
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
@Schema(description = "Информация о банкомате")
public class AtmDTO {
    @Schema(description = "Адресс, по которому расположен банкомат")
    @NotNull
    @NotBlank
    private String address;
    @Schema(description = "Начало работы банкомата")
    @NotNull
    private LocalDateTime startOfWork;
    @Schema(description = "Конец работы банкомата")
    @NotNull
    private LocalDateTime endOfWork;
    @Schema(description = "Работает ли банкомат круглосуточно")
    private boolean allHours;
}
