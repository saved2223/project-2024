package ds.project.service;

import ds.project.dto.StudentMarkDto;
import ds.project.dto.TaskForMarkDTO;
import ds.project.model.Task;
import ds.project.model.mark.Mark;
import ds.project.model.mark.MarkResult;
import ds.project.model.mark.MarkResultEnum;
import ds.project.model.person.Person;
import ds.project.model.student.Student;
import ds.project.repository.MarkRepository;
import ds.project.repository.MarkResultRepository;
import ds.project.repository.StudentRepository;
import ds.project.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

class MarksServiceTest {
    @Mock
    MarkRepository markRepository;
    @Mock
    TaskRepository taskRepository;
    @Mock
    StudentRepository studentRepository;
    @Mock
    MarkResultRepository markResultRepository;
    @Mock
    MessageService messageService;
    @InjectMocks
    MarksService marksService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetStudentsMarksByPerson() {
        when(markRepository.findByStudent_Trainings_IdAndStudent_Person(any(UUID.class), any(Person.class))).thenReturn(Arrays.<Mark>asList(new Mark()));

        List<StudentMarkDto> result = marksService.getStudentsMarksByPerson(new Person(), new UUID(0L, 0L));
        Assertions.assertEquals(Arrays.<StudentMarkDto>asList(new StudentMarkDto("taskName", "result")), result);
    }

    @Test
    void testMarkStudentsTask() {
        when(markRepository.getReferenceById(any(UUID.class))).thenReturn( new Mark());
        when(markRepository.save(any(Mark.class))).thenReturn(new Mark());
        when(taskRepository.getReferenceById(any(UUID.class))).thenReturn(new Task());
        when(taskRepository.save(any(Task.class))).thenReturn(new Task());
        when(studentRepository.getReferenceById(any(UUID.class))).thenReturn(new Student());
        when(studentRepository.save(any(Student.class))).thenReturn(new Student());
        when(markResultRepository.findByMarkResult(any(MarkResultEnum.class))).thenReturn(null);
        when(markResultRepository.getReferenceById(any(Integer.class))).thenReturn(new MarkResult());
        when(markResultRepository.save(any(MarkResult.class))).thenReturn(new MarkResult());

        marksService.markStudentsTask(new TaskForMarkDTO(new UUID(0L, 0L), "mark", "comment", new UUID(0L, 0L)), new Person());
        verify(messageService).sendMessageToPerson(anyString(), any(Person.class), any(Person.class));
    }
}
