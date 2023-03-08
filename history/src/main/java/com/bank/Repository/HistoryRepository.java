package src.main.java.com.bank.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.main.java.com.bank.Entity.HistoryEntity;


@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {

}
