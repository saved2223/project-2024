package ds.project.repository;

import ds.project.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    //select task.name as task_name, person.username, result.mark_result from task
    //join lesson_tasks as lt on task.id = lt.tasks_id
    //join lesson on lesson.id = lt.lesson_id
    //join training on lesson.training_id = training.id
    //join mark on task.id = mark.task_id
    //join mark_result as result on mark.result_id = result.id
    //join student on mark.student_id = student.id
    //join person on student.person_id = person.id
}