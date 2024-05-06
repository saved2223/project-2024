package ds.project.controller;

import ds.project.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/admin/")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @GetMapping("publishLesson")
    public ResponseEntity<?> publishLesson(String lessonId){
        lessonService.publishLesson(UUID.fromString(lessonId));
        return ResponseEntity.ok().build();
    }
}
