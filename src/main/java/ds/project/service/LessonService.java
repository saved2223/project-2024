package ds.project.service;

import ds.project.model.Lesson;
import ds.project.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;
    //todo как взять активных пользователей, как сделать форк
}
