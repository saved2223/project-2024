package ds.project.controller;

import ds.project.dto.AllMarksRecordDTO;
import ds.project.dto.TaskForMarkDTO;
import ds.project.model.person.Person;
import ds.project.service.MarksService;
import ds.project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private MarksService marksService;

    @GetMapping("createAllMarksList")
    public ResponseEntity<List<AllMarksRecordDTO>> createAllMarksList(@RequestParam String trainingId){
        return ResponseEntity.ok().body(taskService.getAllMarksRecord(trainingId));
    }

    @PostMapping("markTask")
    public ResponseEntity<?> markATask(@RequestBody TaskForMarkDTO taskForMarkDTO){
        marksService.markStudentsTask(taskForMarkDTO, getCurrentUser());
        return ResponseEntity.ok().build();
    }

    private Person getCurrentUser() {
        return (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
