package com.bank.publicinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Класс BankDetailsDTO — это объект передачи данных (DTO),
 * представляющий информацию о деталях банка.
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
@Schema(description = "Информация о деталях банка")
public class BankDetailsDTO {
    @Schema(description = "Банковский идентификационный код")
    @NotNull
    @Size(min = 9, max = 9)
    private Long bik;
    @Schema(description = "Идентификационный номер налогоплательщика")
    @NotNull
    @Size(min = 7, max = 7)
    private Long inn;
    @Schema(description = "Код причины постановки клиента")
    @NotNull
    @Size(min = 9, max = 9)
    private Long  kpp;
    @Schema(description = "Корреспондентский счет")
    @NotNull
    @Size(min = 5, max = 20)
    private Long corAccount;
    @Schema(description = "Город, котором зарегистрирован юр. адрес банка")
    @NotNull
    @NotBlank
    private String city;
    @Schema(description = "Акционерное общество")
    @NotNull
    @NotBlank
    private String jointStockCompany;
    @Schema(description = "Имя банка")
    @NotNull
    @NotBlank
    private String name;
}
