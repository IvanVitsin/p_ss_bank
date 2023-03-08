package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.LicenseDTO;
import com.bank.publicinfo.service.LicenseService;
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
@RequestMapping("/license")
@Tag(name = "Лицензия", description = "Данные о лицензии")
public class LicenseController {
    private final LicenseService licenseService;

    @Operation(summary = "Получить все данные о лицензиях",
            description = "Этот API возвращает список всех данных о лицензиях")
    @ApiResponse(responseCode = "200", description = "Успешно получены все данные о лицензиях")
    @GetMapping
    public ResponseEntity<List<LicenseDTO>> getLicense() {
        return new ResponseEntity<>(licenseService
                .getLicenseInfo(),
                HttpStatus.OK);
    }

    @Operation(summary = "Добавить лицензию банка",
            description = "Этот API позволяет добавить лицензию банка")
    @ApiResponse(responseCode = "200", description = "Лицензия банка успешно добавлена")
    @PostMapping("/{id}")
    public ResponseEntity<LicenseDTO> addLicense(@RequestBody
                                                 @Valid LicenseDTO licenseDTO,
                                                 @PathVariable("id")
                                                 @Parameter(description = "Идентификатор лицензии ID") Long id) {
        return new ResponseEntity<>(licenseService
                .createLicense(licenseDTO, id),
                HttpStatus.OK);
    }

    @Operation(summary = "Обновить информацию о лицензии",
            description = "Этот API позволяет обновить существующую информацию о лицензии")
    @ApiResponse(responseCode = "200", description = "Информация о лицензии успешна обновлена")
    @PutMapping("/{id}")
    public ResponseEntity<LicenseDTO> updateLicense(@RequestBody
                                                    @Valid LicenseDTO licenseDTO,
                                                    @PathVariable("id")
                                                    @Parameter(description = "Идентификатор лицензии ID") Long id) {
        return new ResponseEntity<>(licenseService
                .updateLicense(licenseDTO, id),
                HttpStatus.OK);
    }

    @Operation(summary = "Удалить информацию о лицензии по ID",
            description = "Этот API позволяет удалить существующую информацию о лицензии по ID")
    @ApiResponse(responseCode = "200", description = "Информация о лицензии банка успешна удалена")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicense(@PathVariable("id")
                                              @Parameter(description = "Идентификатор лицензии ID") Long id) {
        licenseService.deleteLicense(id);
        return ResponseEntity.ok().build();
    }
}
