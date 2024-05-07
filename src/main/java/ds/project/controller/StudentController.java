package ds.project.controller;

import ds.project.dto.StudentMarkDto;
import ds.project.dto.TrainingCreationDto;
import ds.project.dto.student.StudentRegisterDto;
import ds.project.model.person.Person;
import ds.project.service.MarksService;
import ds.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private MarksService marksService;

    @PostMapping("becomeAStudent")
    public ResponseEntity<?> becomeAStudent(@RequestBody StudentRegisterDto studentDto) {
        return ResponseEntity.ok().body(
                studentService.becomeStudent
                        (studentDto, getCurrentUser()));
    }

    @PostMapping("enrollATraining")
    public ResponseEntity<TrainingCreationDto> enrollATraining(@RequestParam String trainingId) {
        return ResponseEntity.ok().body(studentService.enrollATraining(
                UUID.fromString(trainingId), getCurrentUser()));
    }

    @GetMapping("getMarks")
    public ResponseEntity<List<StudentMarkDto>> getMarks(@RequestParam String trainingId) {
        return ResponseEntity.ok().body(
                marksService.getStudentsMarksByPerson(getCurrentUser(), UUID.fromString(trainingId)));
    }

    private Person getCurrentUser() { //todo переделать на общий метод
        return (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
