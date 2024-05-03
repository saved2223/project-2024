package ds.project.model.mark;

import ds.project.model.Task;
import ds.project.model.person.Person;
import ds.project.model.student.Student;
import ds.project.model.training.TrainingStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "mark_result")
public class MarkResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "mark_result", nullable = false, unique = true)
    private MarkResultEnum markResult;

}