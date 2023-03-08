package com.bank.publicinfo.util.exceptions;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * ErrorDto - объект передачи данных, который используется для хранения информации об ошибках.
 * <p>
 * Применяются аннотации из lombok - @Data, @Builder, @NoArgsConstructor и @AllArgsConstructor
 * для автоматического создания геттеров, сеттеров, построителей и конструкторов для класса.
 *
 * @author Semushkin Danila
 * @version 1.0
 * @since 15.02.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    /**
     * Поле error - строковое представления сообщения об ошибке.
     * Поле errorDescription - строковое представление сообщения об ошибке с более подробным её описанием.
     */
    private String error;
    @JsonProperty("error_description")
    private String errorDescription;
}

