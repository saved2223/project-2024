package ds.project.service;

import ds.project.dto.TrainingCreationDto;
import ds.project.dto.student.StudentDto;
import ds.project.dto.student.StudentRegisterDto;
import ds.project.exception.AlmostAStudentException;
import ds.project.exception.AlreadyInTrainingException;
import ds.project.exception.ToLateTrainingEnrollingException;
import ds.project.exception.WrongTrainingIdException;
import ds.project.mapper.StudentMapper;
import ds.project.mapper.TrainingCreationMapper;
import ds.project.model.person.Person;
import ds.project.model.student.Student;
import ds.project.mapper.StudentRegisterDtoMapper;
import ds.project.model.training.Training;
import ds.project.repository.StudentRepository;
import ds.project.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentRegisterDtoMapper studentRegisterDtoMapper;
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private TrainingCreationMapper trainingCreationMapper;

    public StudentDto becomeStudent(StudentRegisterDto studentRegisterDto, Person person) {
        Optional<Student> student = studentRepository.findByPerson_Username(person.getUsername());
        if (student.isPresent()) {
            throw new AlmostAStudentException();
        } else {
            Student newStudent = studentRegisterDtoMapper.toEntity(studentRegisterDto);
            newStudent.setPerson(person);
            studentRepository.save(newStudent);
            return studentMapper.toDto(newStudent);
        }
    }

    public TrainingCreationDto enrollATraining(UUID trainingId, Person person) {
        Training training = trainingRepository.findById(trainingId).orElse(null);
        if(training == null) {
            throw new WrongTrainingIdException();
        }
        if(new Date(System.currentTimeMillis()).after(training.getRegistrationDeadline())){
            throw new ToLateTrainingEnrollingException(training.getRegistrationDeadline());
        }
        Student student = studentRepository.getReferenceById(person.getId());
        if(student.getTrainings().contains(training)){
            throw new AlreadyInTrainingException();
        }
        student.getTrainings().add(training);
        studentRepository.save(student);
        return trainingCreationMapper.toDto(training);
    }
}
