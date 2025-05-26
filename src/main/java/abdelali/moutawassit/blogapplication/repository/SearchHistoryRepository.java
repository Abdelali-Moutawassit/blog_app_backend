package abdelali.moutawassit.blogapplication.repository;

import abdelali.moutawassit.blogapplication.model.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    // 🔍 Récupérer les recherches d’un utilisateur, triées par date décroissante
    List<SearchHistory> findByUserIdOrderBySearchedAtDesc(Long userId);

    // 🧹 Supprimer tout l’historique d’un utilisateur
    void deleteByUserId(Long userId);
}