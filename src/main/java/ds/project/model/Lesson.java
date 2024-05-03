package ds.project.model;

import ds.project.model.training.Training;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    @ManyToMany
    @JoinTable(name = "lesson_tasks",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "tasks_id"))
    private Set<Task> tasks = new LinkedHashSet<>();

}