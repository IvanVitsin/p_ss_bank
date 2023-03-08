package src.main.java.com.bank.Controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.main.java.com.bank.DTO.HistoryDTO;
import src.main.java.com.bank.Entity.HistoryEntity;
import src.main.java.com.bank.Service.HistoryService;

import java.util.List;


@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@RequestMapping( "/api")
@Api(description = "Контроллер таблицы Истории") //Помечает класс как ресурс Swagger
public class RestController {

    private final HistoryService historyService;

    @GetMapping()
    @ApiOperation("Получение списка всех записей") //Описывает операцию
    public ResponseEntity<List<HistoryEntity>> getHistory() {
        return new ResponseEntity<>(historyService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @ApiOperation("Создание записей")
    public ResponseEntity<HistoryDTO> saveHistory(@PathVariable("id") Long id, @RequestBody HistoryDTO historyDTO) {
        return new ResponseEntity<>(historyService.saveHistory(id, historyDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation("Обновление записей")
    public ResponseEntity<HistoryDTO> updateHistory(@PathVariable("id") Long id,@RequestBody HistoryDTO historyDTO) {
        return new ResponseEntity<>(historyService.updateHistory(id,historyDTO), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @ApiOperation("Удаление записей")
    public ResponseEntity<HistoryDTO> deleteHistory(@PathVariable("id")  Long id, @RequestBody HistoryDTO historyDTO, @PathVariable String ig) {
        return new ResponseEntity<>(historyService.deleteHistory(id,historyDTO), HttpStatus.OK);
    }
}
