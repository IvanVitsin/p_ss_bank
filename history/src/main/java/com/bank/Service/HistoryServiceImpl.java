package src.main.java.com.bank.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.main.java.com.bank.DTO.HistoryDTO;
import src.main.java.com.bank.Entity.HistoryEntity;
import src.main.java.com.bank.Mapper.HistoryMapper;
import src.main.java.com.bank.Repository.HistoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    @Transactional
    public List<HistoryEntity> getAll() {
        return historyRepository.findAll();
    }

    @Override
    @Transactional
    public HistoryDTO saveHistory(Long id, HistoryDTO saveHistory) {
        HistoryEntity historyEntity = HistoryMapper.MAPPER.toHistory(saveHistory);
        HistoryEntity historyEntity1 = historyRepository.save(historyEntity);
        return HistoryMapper.MAPPER.toHistoryDTO(historyEntity1);
    }
    @Override
    @Transactional
    public HistoryDTO updateHistory(Long id, HistoryDTO updateHistory) {
        HistoryEntity historyEntity = HistoryMapper.MAPPER.toHistory(updateHistory);
        HistoryEntity historyEntity2 = historyRepository.save(historyEntity);
        return HistoryMapper.MAPPER.toHistoryDTO(historyEntity2);
    }

    @Override
    @Transactional
    public HistoryDTO deleteHistory(Long id, HistoryDTO updateHistory) {
        HistoryEntity historyEntity = HistoryMapper.MAPPER.toHistory(updateHistory);
        HistoryEntity historyEntity1 = historyRepository.save(historyEntity);
        return HistoryMapper.MAPPER.toHistoryDTO(historyEntity1);
    }
}

