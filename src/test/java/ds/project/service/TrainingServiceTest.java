package ds.project.service;

import ds.project.dto.TrainingCreationDto;
import ds.project.mapper.TrainingCreationMapper;
import ds.project.model.training.Training;
import ds.project.model.training.TrainingStatus;
import ds.project.model.training.TrainingStatusEnum;
import ds.project.repository.TrainingRepository;
import ds.project.repository.TrainingStatusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.UUID;

import static org.mockito.Mockito.*;

class TrainingServiceTest {
    @Mock
    TrainingRepository trainingRepository;
    @Mock
    TrainingStatusRepository trainingStatusRepository;
    @Mock
    TrainingCreationMapper trainingMapper;
    @Mock
    GitlabService gitlabService;
    @InjectMocks
    TrainingService trainingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTraining() {
        when(trainingRepository.save(any(Training.class))).thenReturn(new Training());
        when(trainingStatusRepository.findByTrainingStatus(any(TrainingStatusEnum.class))).thenReturn(null);
        when(trainingStatusRepository.save(any(TrainingStatus.class))).thenReturn(new TrainingStatus());
        when(trainingMapper.toEntity(any(TrainingCreationDto.class))).thenReturn(new Training());
        when(trainingMapper.toDto(any(Training.class))).thenReturn(new TrainingCreationDto("name", "description", new Date(0, 0, 0), new Date(0, 0, 0), new Date(0, 0, 0)));

        TrainingCreationDto result = trainingService.createTraining(new TrainingCreationDto("name", "description", new Date(0, 0, 0), new Date(0, 0, 0), new Date(0, 0, 0)));
        Assertions.assertEquals(new TrainingCreationDto("name", "description", new Date(0, 0, 0), new Date(0, 0, 0), new Date(0, 0, 0)), result);
    }

    @Test
    void testStartTraining() {
        when(trainingRepository.getReferenceById(any(UUID.class))).thenReturn(new Training());
        when(trainingRepository.save(any(Training.class))).thenReturn(new Training());
        when(trainingStatusRepository.findByTrainingStatus(any(TrainingStatusEnum.class))).thenReturn(null);
        when(trainingStatusRepository.getReferenceById(any(Integer.class))).thenReturn(new TrainingStatus());
        when(trainingStatusRepository.save(any(TrainingStatus.class))).thenReturn(new TrainingStatus());

        trainingService.startTraining(null);
        verify(gitlabService).createGitlabUsersForTraining(any(UUID.class));
    }
}