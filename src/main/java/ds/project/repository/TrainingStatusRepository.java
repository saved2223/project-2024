package ds.project.repository;

import ds.project.model.training.TrainingStatus;
import ds.project.model.training.TrainingStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainingStatusRepository extends JpaRepository<TrainingStatus, Integer> {
    Optional<TrainingStatus> findByTrainingStatus(TrainingStatusEnum trainingStatus);
}