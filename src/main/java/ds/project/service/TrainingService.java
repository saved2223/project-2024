package ds.project.service;

import ds.project.dto.TrainingCreationDto;
import ds.project.mapper.TrainingCreationMapper;
import ds.project.model.training.Training;
import ds.project.model.training.TrainingStatus;
import ds.project.model.training.TrainingStatusEnum;
import ds.project.repository.TrainingRepository;
import ds.project.repository.TrainingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private TrainingStatusRepository trainingStatusRepository;
    @Autowired
    private TrainingCreationMapper trainingMapper;
    @Autowired
    GitlabService gitlabService;


    public TrainingCreationDto createTraining(TrainingCreationDto trainingDto) {
        Training training = trainingMapper.toEntity(trainingDto);
        training.setTrainingStatus(getTrainingStatusByName(TrainingStatusEnum.NOT_STARTED));
        trainingRepository.save(training);
        return trainingMapper.toDto(training);
    }

    public void startTraining(UUID trainingId) {
        Training training = trainingRepository.getReferenceById(trainingId);
        training.setTrainingStatus(getTrainingStatusByName(TrainingStatusEnum.STARTED));
        trainingRepository.save(training);
        gitlabService.createGitlabUsersForTraining(trainingId);
    }

    private TrainingStatus getTrainingStatusByName(TrainingStatusEnum trainingStatusEnum) {
        return trainingStatusRepository.findByTrainingStatus(trainingStatusEnum).get();
    }
}
