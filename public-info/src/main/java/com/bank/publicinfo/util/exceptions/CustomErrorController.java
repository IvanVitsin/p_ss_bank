package com.bank.publicinfo.util.exceptions;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * Класс CustomErrorController реализует ErrorController для обработки ошибок.
 * <p>
 * Этот класс используется для обработки и возврата клиенту ответа об ошибке.
 *
 * @author Semushkin Danila
 * @version 1.0
 * @since 15.02.2023
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Controller
public class CustomErrorController implements ErrorController {
    /**
     * Поле PATH - строковое представление адреса об ошибке.
     * Поле errorAttributes - экземпляр интерфейса ErrorAttributes
     */
    private static final String PATH = "/error";
    ErrorAttributes errorAttributes;

    /**
     * Метод error сопоставляется с путем «/error» и возвращает объект ответа с подробностями ошибки в теле.
     *
     * @param webRequest объект веб-запроса.
     * @return ResponseEntity Объект ответа с подробностями об ошибке.
     */
    @RequestMapping(CustomErrorController.PATH)
    public ResponseEntity<ErrorDTO> error(WebRequest webRequest) {
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(
                webRequest,
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.EXCEPTION, ErrorAttributeOptions.Include.MESSAGE)
        );
        return ResponseEntity
                .status((Integer) attributes.get("status"))
                .body(ErrorDTO
                        .builder()
                        .error((String) attributes.get("error"))
                        .errorDescription((String) attributes.get("message"))
                        .build()
                );
    }
}
