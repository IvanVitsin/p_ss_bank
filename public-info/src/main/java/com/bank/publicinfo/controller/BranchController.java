package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BranchDTO;
import com.bank.publicinfo.service.BranchService;
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
@RequestMapping("/branch")
@Tag(name = "Отделение", description = "Данные об отделении банка")
public class BranchController {
    private final BranchService branchService;

    @Operation(summary = "Получить все данные об отделении банка",
            description = "Этот API возвращает список всех данных об отделении банка")
    @ApiResponse(responseCode = "200", description = "Успешно получены все данные об отделении банка")
    @GetMapping()
    public ResponseEntity<List<BranchDTO>> getBranchInfo() {
        return new ResponseEntity<>(branchService
                .getBranch(),
                HttpStatus.OK);
    }

    @Operation(summary = "Добавить информацию об отделении банка",
            description = "Этот API позволяет добавить информацию об отделении банка")
    @ApiResponse(responseCode = "200", description = "Информация об отделении банка успешна добавлена")
    @PostMapping()
    public ResponseEntity<BranchDTO> addBranch(@RequestBody
                                                  @Valid BranchDTO branchDTO) {
        return new ResponseEntity<>(branchService
                .createBranch(branchDTO),
                HttpStatus.OK);
    }

    @Operation(summary = "Обновить существующую информацию об отделении банка",
            description = "Этот API позволяет обновить существующую информацию об отделении банка")
    @ApiResponse(responseCode = "200", description = "Информация об отделении банка успешна обновлена")
    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> updateBranch(@RequestBody
                                                  @Valid BranchDTO branchDTO, @PathVariable("id")
                                                  @Parameter(description = "Идентификатор отделения банка ID") Long id) {
        return new ResponseEntity<>(branchService
                .updateBranch(branchDTO, id),
                HttpStatus.OK);
    }

    @Operation(summary = "Удалить информацию об отделении банка по ID",
            description = "Этот API позволяет удалить существующую информацию об отделении банка по ID")
    @ApiResponse(responseCode = "200", description = "Информация об отделении банка успешна удалена")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBranch(@PathVariable("id") Long id) {
        branchService.deleteBranch(id);
        return new ResponseEntity<>("Данные об отделении удалены", HttpStatus.OK);
    }
}
