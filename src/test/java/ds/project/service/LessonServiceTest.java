package ds.project.service;

import ds.project.dto.CheckLessonTasksDTO;
import ds.project.model.Task;
import ds.project.repository.LessonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.*;

class LessonServiceTest {
    @Mock
    LessonRepository lessonRepository;
    @Mock
    GitlabService gitlabService;
    @InjectMocks
    LessonService lessonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPublishLesson() {
        when(lessonRepository.findById(any(UUID.class))).thenReturn(null);

        lessonService.publishLesson(new UUID(0L, 0L));
        verify(gitlabService).forkAllTaskReposToActiveUsers(any(Set.class));
    }

    @Test
    void testCheckLessonTasksByStringLessonUuid() {
        when(lessonRepository.findUnPassedTasksByLessonUuid(anyString())).thenReturn(Arrays.<Task>asList(new Task()));
        when(gitlabService.getAllCommitsForTask(any(Task.class))).thenReturn(Arrays.<CheckLessonTasksDTO>asList(new CheckLessonTasksDTO(new UUID(0L, 0L), "taskName", "userID", "username", new Date(0, 0, 0), "commitAuthorUsername", "commitLink")));

        List<CheckLessonTasksDTO> result = lessonService.checkLessonTasksByStringLessonUuid("lessonId");
        Assertions.assertEquals(Arrays.<CheckLessonTasksDTO>asList(new CheckLessonTasksDTO(new UUID(0L, 0L), "taskName", "userID", "username", new Date(0, 0, 0), "commitAuthorUsername", "commitLink")), result);
    }
}

