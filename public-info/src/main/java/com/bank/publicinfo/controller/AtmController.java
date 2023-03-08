package com.bank.publicinfo.controller;


import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.service.AtmService;
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
@RequestMapping("/atm")
@Tag(name = "Банкомат", description = "Данные о банкомате")
public class AtmController {
    private final AtmService atmService;

    @Operation(summary = "Получить все данные о банкомате",
            description = "Этот API возвращает список всех данных о банкомате")
    @ApiResponse(responseCode = "200", description = "Успешно получены все данные о банкомате")
    @GetMapping()
    public ResponseEntity<List<AtmDTO>> getAllAtmInfo() {
        return new ResponseEntity<>(atmService
                .getAllAtmInfo(),
                HttpStatus.OK);
    }

    @Operation(summary = "Добавить новый банкомат",
            description = "Этот API позволяет добавить новый банкомат")
    @ApiResponse(responseCode = "200", description = "Новый банкомат успешно добавлен")
    @PostMapping("/{id}")
    public ResponseEntity<AtmDTO> addAtm(@RequestBody
                                         @Valid AtmDTO atmDTO,
                                         @PathVariable("id")
                                         @Parameter(description = "Идентификатор банкомата ID")
                                         Long id) {
        return new ResponseEntity<>(atmService
                .createAtm(atmDTO, id),
                HttpStatus.OK);
    }

    @Operation(summary = "Обновить существующий банкомат",
            description = "Этот API позволяет обновить существующий банкомат")
    @ApiResponse(responseCode = "200", description = "Информация о банкомате успешна обновлена")
    @PutMapping("/{id}")
    public ResponseEntity<AtmDTO> updateAtm(@RequestBody
                                            @Valid AtmDTO atmDTO,
                                            @PathVariable("id")
                                            @Parameter(description = "Идентификатор банкомата ID") Long id) {
        return new ResponseEntity<>(atmService.updateAtm(atmDTO, id),
                HttpStatus.OK);
    }

    @Operation(summary = "Удалить банкомат по ID",
            description = "Этот API позволяет удалить существующий банкомат по ID")
    @ApiResponse(responseCode = "200", description = "Информация о банкомате успешна удалена")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtm(@PathVariable("id") Long id) {
        atmService.deleteAtm(id);
        return ResponseEntity.ok().build();
    }
}
