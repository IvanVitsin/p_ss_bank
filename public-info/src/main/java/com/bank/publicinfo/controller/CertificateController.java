package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.CertificateDTO;
import com.bank.publicinfo.service.CertificateService;
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
@RequestMapping("/certificate")
@Tag(name = "Сертификаты банка", description = "Данные о сертификатах банка")
public class CertificateController {
    private final CertificateService certificateService;

    @Operation(summary = "Получить все данные о сертификатах",
            description = "Этот API возвращает список всех данных о сертификатах")
    @ApiResponse(responseCode = "200", description = "Успешно получены все данные о сертификатах")
    @GetMapping
    public ResponseEntity<List<CertificateDTO>> getAllCertificates() {
        return new ResponseEntity<>(certificateService
                .getCertificateInfo(),
                HttpStatus.OK);
    }

    @Operation(summary = "Добавить сертификат банка",
            description = "Этот API позволяет добавить сертификат банка")
    @ApiResponse(responseCode = "200", description = "Сертификат банка успешно добавлен")
    @PostMapping("/{id}")
    public ResponseEntity<CertificateDTO> addCertificate(@RequestBody
                                                         @Valid CertificateDTO certificateDTO,
                                                         @PathVariable("id")
                                                         @Parameter(description = "Идентификатор сертификата ID") Long id) {
        return new ResponseEntity<>(certificateService
                .createCertificate(certificateDTO, id),
                HttpStatus.OK);
    }

    @Operation(summary = "Обновить информацию о сертификате",
            description = "Этот API позволяет обновить существующую информацию о сертификате")
    @ApiResponse(responseCode = "200", description = "Информация о сертификате успешна обновлена")
    @PutMapping("/{id}")
    public ResponseEntity<CertificateDTO> updateCertificate(@RequestBody
                                                            @Valid CertificateDTO certificateDTO,
                                                            @PathVariable("id")
                                                            @Parameter(description = "Идентификатор сертификата ID") Long id) {
        return new ResponseEntity<>(certificateService
                .updateCertificate(certificateDTO, id),
                HttpStatus.OK);
    }

    @Operation(summary = "Удалить информацию о сертификате по ID",
            description = "Этот API позволяет удалить существующую информацию о сертификате по ID")
    @ApiResponse(responseCode = "200", description = "Информация о сертификате банка успешна удалена")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable("id")
                                                  @Parameter(description = "Идентификатор сертификата ID") Long id) {
        certificateService.deleteCertificate(id);
        return ResponseEntity.ok().build();
    }
}
