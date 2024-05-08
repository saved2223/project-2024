package ds.project.controller;

import ds.project.dto.TrainingCreationDto;
import ds.project.service.GitlabService;
import ds.project.service.TrainingService;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @PostMapping("addTraining")
    public ResponseEntity<TrainingCreationDto> addTraining(@RequestBody TrainingCreationDto training) {
        return ResponseEntity.ok(trainingService.createTraining(training));
    }

    @GetMapping("startTraining")
    public ResponseEntity<?> startTraining(@RequestParam String trainingId) {
        trainingService.startTraining(UUID.fromString(trainingId));
        return ResponseEntity.ok().build();
    }



}
