package src.main.java.com.bank.Service;

import src.main.java.com.bank.DTO.HistoryDTO;
import src.main.java.com.bank.Entity.HistoryEntity;

import java.util.List;

public interface HistoryService {

    List<HistoryEntity> getAll();

    HistoryDTO saveHistory(Long id, HistoryDTO saveHistory);
    HistoryDTO updateHistory(Long id, HistoryDTO updateHistory);
    HistoryDTO deleteHistory(Long id, HistoryDTO updateHistory);

}
