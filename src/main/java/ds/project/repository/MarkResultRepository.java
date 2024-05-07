package ds.project.repository;

import ds.project.model.mark.MarkResult;
import ds.project.model.mark.MarkResultEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarkResultRepository extends JpaRepository<MarkResult, Integer> {
    Optional<MarkResult> findByMarkResult(MarkResultEnum markResult);

}
