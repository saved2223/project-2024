package ds.project.controller;

import ds.project.dto.CheckLessonTasksDTO;
import ds.project.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    @GetMapping("checkTasks")
    public ResponseEntity<List<CheckLessonTasksDTO>> checkLessonTasks (@RequestParam String lessonId){
        return ResponseEntity.ok(lessonService.checkLessonTasksByStringLessonUuid(lessonId));
    }
}
