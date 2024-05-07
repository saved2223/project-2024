package ds.project.service;

import ds.project.dto.AllMarksRecordDTO;
import ds.project.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<AllMarksRecordDTO> getAllMarksRecord(String trainingId) {
        return taskRepository.getAllMarksRecords(trainingId);
    }

}
