package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.service.BankDetailsService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Api
@RestController
@AllArgsConstructor
@RequestMapping("/details")
@Tag(name = "Детали банка", description = "Данные о деталях банка")
public class BankDetailsController {
    private final BankDetailsService bankDetailsService;

    @Operation(summary = "Получить все данные о банке",
            description = "Этот API возвращает список всех данных о банке")
    @ApiResponse(responseCode = "200", description = "Успешно получены все данные о банке")
    @GetMapping
    public ResponseEntity<List<BankDetailsDTO>> getAllBankDetails() {
        return new ResponseEntity<>(bankDetailsService
                .getAllBankDetails(),
                HttpStatus.OK);
    }

    @Operation(summary = "Добавить информацию о деталях банка",
            description = "Этот API позволяет добавить информацию о банке")
    @ApiResponse(responseCode = "200", description = "Информация о банке успешна добавлена")
    @PostMapping
    public ResponseEntity<BankDetailsDTO> addBankDetails(@RequestBody
                                                         @Valid BankDetailsDTO bankDetailsDTO) {
        return new ResponseEntity<>(bankDetailsService.createBankDetails(bankDetailsDTO),
                HttpStatus.OK);
    }

    @Operation(summary = "Обновить существующую информацию о банке",
            description = "Этот API позволяет обновить существующую информацию о банке")
    @ApiResponse(responseCode = "200", description = "Информация о банке успешна обновлена")
    @PutMapping("/{id}")
    public ResponseEntity<BankDetailsDTO> updateBankDetails(@RequestBody
                                                            @Valid BankDetailsDTO bankDetailsDTO,
                                                            @PathVariable("id")
                                                            @Parameter(description = "Идентификатор деталей о банке ID")
                                                            Long id) {
        return new ResponseEntity<>(bankDetailsService.updateBankDetails(bankDetailsDTO, id),
                HttpStatus.OK);
    }

    @Operation(summary = "Удалить информацию о банке по ID",
            description = "Этот API позволяет удалить существующую информацию о банке по ID")
    @ApiResponse(responseCode = "200", description = "Информация о банке успешна удалена")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deteleBankDetails(@PathVariable("id")
                                                    @Parameter(description = "Идентификатор деталей о банке ID")
                                                    Long id) {
        bankDetailsService.deleteBankDetails(id);
        return new ResponseEntity<>("Данные успешно удалены",
                HttpStatus.OK);
    }
}
