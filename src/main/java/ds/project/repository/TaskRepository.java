package ds.project.repository;

import ds.project.dto.AllMarksRecordDTO;
import ds.project.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query(value = "select task.name as taskName, person.username as userName, result.mark_result as markResult\n" +
            "from task\n" +
            "         join lesson_tasks as lt on task.id = lt.tasks_id\n" +
            "         join lesson on lesson.id = lt.lesson_id\n" +
            "         join training on lesson.training_id = training.id\n" +
            "         join mark on task.id = mark.task_id\n" +
            "         join mark_result as result on mark.result_id = result.id\n" +
            "         join student on mark.student_id = student.id\n" +
            "         join person on student.person_id = person.id\n" +
            "where training_id=?1",  nativeQuery = true)
    List<AllMarksRecordDTO> getAllMarksRecords(String trainingId);

}