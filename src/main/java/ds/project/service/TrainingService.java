package ds.project.service;

import ds.project.dto.TrainingDto;
import ds.project.mapper.TrainingMapper;
import ds.project.model.training.Training;
import ds.project.model.training.TrainingStatus;
import ds.project.model.training.TrainingStatusEnum;
import ds.project.repository.TrainingRepository;
import ds.project.repository.TrainingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private TrainingStatusRepository trainingStatusRepository;
    @Autowired
    private TrainingMapper trainingMapper;


    public TrainingDto createTraining(TrainingDto trainingDto) {
        TrainingStatus trainingNotStartedStatus = trainingStatusRepository
                .findByTrainingStatus(TrainingStatusEnum.NOT_STARTED)
                .get();
        Training training = trainingMapper.toEntity(trainingDto);
        training.setTrainingStatus(trainingNotStartedStatus);
        trainingRepository.save(training);
        return trainingMapper.toDto(training);

    }
}
