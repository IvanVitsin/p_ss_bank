package com.bank.transfer.dto;

import com.bank.transfer.validation.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Сущность перевода по номеру банковского счета")
public class AccountTransferDto {

    @Account(message = "Введенный номер должен иметь 12 цифр")
    @Schema(description = "Номер банковского счета")
    private Long accountNumber;

    @Schema(description = "Сумма перевода")
    @NotNull(message = "Не указана сумма перевода")
    @Min(value = 100, message = "Сумма перевода не может быть меньше 100")
    @Max(value = 1000000, message = "Сумма перевода не может быть больше 1000000")
    private Double amount;

    @Schema(description = "Цель перевода")
    private String purpose;

    @Schema(description = "технический идентификатор банковского счета")
    @NotNull(message = "Не указан технический идентификатор банковского счета")
    private Long accountDetailsId;

}
