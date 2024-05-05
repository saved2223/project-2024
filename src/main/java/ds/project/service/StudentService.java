package ds.project.service;

import ds.project.dto.student.StudentDto;
import ds.project.dto.student.StudentRegisterDto;
import ds.project.exception.AlmostAStudentException;
import ds.project.mapper.StudentMapper;
import ds.project.model.person.Person;
import ds.project.model.student.Student;
import ds.project.mapper.StudentRegisterDtoMapper;
import ds.project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentRegisterDtoMapper studentRegisterDtoMapper;

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
}
