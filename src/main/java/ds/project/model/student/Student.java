package ds.project.model.student;

import ds.project.model.person.Person;
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
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private Person person;

    @Column(name = "university", nullable = false, length = 100)
    private String university;

    @Column(name = "departament", nullable = false, length = 100)
    private String departament;

    @Column(name = "speciality", nullable = false, length = 50)
    private String speciality;

    @Column(name = "course")
    private Byte course;

    @ManyToOne(optional = false)
    @JoinColumn(name = "study_status_id", nullable = false)
    private StudyStatus studyStatus;

    @ManyToMany
    @JoinTable(name = "student_trainings",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "trainings_id"))
    private Set<Training> trainings = new LinkedHashSet<>();

}