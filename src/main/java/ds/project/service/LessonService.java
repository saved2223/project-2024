package ds.project.service;

import ds.project.dto.CheckLessonTasksDTO;
import ds.project.model.Lesson;
import ds.project.model.Task;
import ds.project.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    GitlabService gitlabService;

    public void publishLesson(UUID lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        gitlabService.forkAllTaskReposToActiveUsers(lesson.getTasks());
    }

    public List<CheckLessonTasksDTO> checkLessonTasksByStringLessonUuid(String lessonId) {
        List<CheckLessonTasksDTO> agregatedCheckingDtos = new ArrayList<>();
        for (Task unpassedTask: lessonRepository.findUnPassedTasksByLessonUuid(lessonId)) {
            agregatedCheckingDtos.addAll(gitlabService.getAllCommitsForTask(unpassedTask));
        }
        return agregatedCheckingDtos;

    }
}
