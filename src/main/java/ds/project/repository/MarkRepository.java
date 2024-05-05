package ds.project.repository;

import ds.project.model.mark.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MarkRepository extends JpaRepository<Mark, UUID> {

}