package ds.project.model.mark;

import ds.project.model.Task;
import ds.project.model.person.Person;
import ds.project.model.student.Student;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "mark")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Person teacher;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne(optional = false)
    @JoinColumn(name = "result_id", nullable = false)
    private MarkResult result;

    @Column(name = "comment")
    private String comment;

    @Column(name = "marked_at")
    private Timestamp markedAt;

}