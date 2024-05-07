package ds.project.controller;

import ds.project.dto.AllMarksRecordDTO;
import ds.project.dto.MessageDto;
import ds.project.dto.PersonRegisterDto;
import ds.project.model.person.Person;
import ds.project.service.MessageService;
import ds.project.service.PersonService;
import ds.project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private MessageService messageService;

    @PostMapping("/public/register")
    public ResponseEntity<Person> register(@RequestBody PersonRegisterDto personRegisterDto){
        return ResponseEntity.ok(personService.registerPerson(personRegisterDto));
    }

    @GetMapping("/anyUser/getAllMessages")
    public ResponseEntity<List<MessageDto>> getAllMessages(){
        return ResponseEntity.ok(messageService.getAllMessagesToPerson(getCurrentUser()));
    }

    private Person getCurrentUser() {
        return (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
