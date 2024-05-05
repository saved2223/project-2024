package ds.project.controller;

import ds.project.dto.student.StudentRegisterDto;
import ds.project.exception.AlmostAStudentException;
import ds.project.model.person.Person;
import ds.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("becomeAStudent")
    public ResponseEntity<?> becomeAStudent(@RequestBody StudentRegisterDto studentDto) {
        return ResponseEntity.ok().body(
                studentService.becomeStudent
                        (studentDto,
                                (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }

}
