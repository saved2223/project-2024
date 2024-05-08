package ds.project.service;

import ds.project.dto.AllMarksRecordDTO;
import ds.project.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class TaskServiceTest {
    @Mock
    TaskRepository taskRepository;
    @InjectMocks
    TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMarksRecord() {
        when(taskRepository.getAllMarksRecords(anyString())).thenReturn(Arrays.<AllMarksRecordDTO>asList(null));

        List<AllMarksRecordDTO> result = taskService.getAllMarksRecord("trainingId");
        Assertions.assertEquals(Arrays.<AllMarksRecordDTO>asList(null), result);
    }
}