package ds.project.repository;

import ds.project.model.Lesson;
import ds.project.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {

    @Query(value = "SELECT t.id, t.name, t.reference_repository\n" +
            "FROM lesson\n" +
            "JOIN public.lesson_tasks lt on lesson.id = lt.lesson_id\n" +
            "JOIN public.task t on t.id = lt.tasks_id\n" +
            "JOIN public.mark m on m.task_id = t.id\n" +
            "JOIN public.mark_result mr on mr.id = m.result_id\n" +
            "WHERE lesson.id = :lessonUuid and mr.mark_result != 'PASS'")
    List<Task>findUnPassedTasksByLessonUuid(@Param("lessonUuid") String lessonUuid);

}