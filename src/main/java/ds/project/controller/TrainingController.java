package ds.project.controller;

import ds.project.dto.TrainingDto;
import ds.project.model.training.Training;
import ds.project.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @PostMapping("addTraining")
    public ResponseEntity<TrainingDto> addTraining(@RequestBody TrainingDto training) {
        return ResponseEntity.ok(trainingService.createTraining(training));
    }
}
