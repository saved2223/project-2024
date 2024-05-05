package ds.project.controller;

import ds.project.dto.PersonRegisterDto;
import ds.project.model.person.Person;
import ds.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/")
public class PersonController {
    @Autowired
    private PersonService personService;


    @PostMapping("register")
    public ResponseEntity<Person> register(@RequestBody PersonRegisterDto personRegisterDto){
        return ResponseEntity.ok(personService.registerPerson(personRegisterDto));
    }

}
