package abdelali.moutawassit.blogapplication.repository;

import abdelali.moutawassit.blogapplication.model.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
}
