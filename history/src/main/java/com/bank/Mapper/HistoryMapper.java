package src.main.java.com.bank.Mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import src.main.java.com.bank.DTO.HistoryDTO;
import src.main.java.com.bank.Entity.HistoryEntity;

@Mapper(componentModel = "history")
public interface HistoryMapper {

    HistoryMapper MAPPER = Mappers.getMapper(HistoryMapper.class);

    HistoryDTO toHistoryDTO(HistoryEntity historyEntity);

    HistoryEntity toHistory(HistoryDTO historyDTO);
}
