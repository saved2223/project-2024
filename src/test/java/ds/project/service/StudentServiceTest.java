package ds.project.service;

import ds.project.dto.TrainingCreationDto;
import ds.project.dto.student.StudentDto;
import ds.project.dto.student.StudentRegisterDto;
import ds.project.mapper.StudentMapper;
import ds.project.mapper.StudentRegisterDtoMapper;
import ds.project.mapper.TrainingCreationMapper;
import ds.project.model.person.Person;
import ds.project.model.student.Student;
import ds.project.model.student.StudyStatusEnum;
import ds.project.model.training.Training;
import ds.project.model.training.TrainingStatusEnum;
import ds.project.repository.StudentRepository;
import ds.project.repository.TrainingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

import static org.mockito.Mockito.*;

class StudentServiceTest {
    @Mock
    StudentRepository studentRepository;
    @Mock
    StudentMapper studentMapper;
    @Mock
    StudentRegisterDtoMapper studentRegisterDtoMapper;
    @Mock
    TrainingRepository trainingRepository;
    @Mock
    TrainingCreationMapper trainingCreationMapper;
    @InjectMocks
    StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBecomeStudent() {
        when(studentRepository.findByPerson_Username(anyString())).thenReturn(null);
        when(studentRepository.save(any(Student.class))).thenReturn(new Student());
        when(studentMapper.toDto(any(Student.class))).thenReturn(new StudentDto("personUsername", "personName", "personEmail", "personPhone", "personTelegram", "personAddInfo", new Date(0, 0, 0), "personCity", "university", "departament", "speciality", Byte.valueOf("00110"), StudyStatusEnum.STUDYING, new HashSet<StudentDto.TrainingDto>(Arrays.asList(new StudentDto.TrainingDto(new UUID(0L, 0L), "name", "description", new Date(0, 0, 0), new Date(0, 0, 0), new Date(0, 0, 0), Integer.valueOf(0), TrainingStatusEnum.NOT_STARTED)))));
        when(studentRegisterDtoMapper.toEntity(any(StudentRegisterDto.class))).thenReturn(new Student());
        when(trainingRepository.save(any(Training.class))).thenReturn(new Training());

        StudentDto result = studentService.becomeStudent(new StudentRegisterDto("university", "departament", "speciality", Byte.valueOf("00110"), StudyStatusEnum.STUDYING), new Person());
        Assertions.assertEquals(new StudentDto("personUsername", "personName", "personEmail", "personPhone", "personTelegram", "personAddInfo", new Date(0, 0, 0), "personCity", "university", "departament", "speciality", Byte.valueOf("00110"), StudyStatusEnum.STUDYING, new HashSet<StudentDto.TrainingDto>(Arrays.asList(new StudentDto.TrainingDto(new UUID(0L, 0L), "name", "description", new Date(0, 0, 0), new Date(0, 0, 0), new Date(0, 0, 0), Integer.valueOf(0), TrainingStatusEnum.NOT_STARTED)))), result);
    }

    @Test
    void testEnrollATraining() {
        when(studentRepository.getReferenceById(any(UUID.class))).thenReturn(new Student());
        when(studentRepository.save(any(Student.class))).thenReturn(new Student());
        when(studentRepository.findById(any(UUID.class))).thenReturn(null);
        when(trainingRepository.getReferenceById(any(UUID.class))).thenReturn(new Training());
        when(trainingRepository.save(any(Training.class))).thenReturn(new Training());
        when(trainingRepository.findById(any(UUID.class))).thenReturn(null);
        when(trainingCreationMapper.toDto(any(Training.class))).thenReturn(new TrainingCreationDto("name", "description", new Date(0, 0, 0), new Date(0, 0, 0), new Date(0, 0, 0)));

        TrainingCreationDto result = studentService.enrollATraining(new UUID(0L, 0L), new Person());
        Assertions.assertEquals(new TrainingCreationDto("name", "description", new Date(0, 0, 0), new Date(0, 0, 0), new Date(0, 0, 0)), result);
    }
}
