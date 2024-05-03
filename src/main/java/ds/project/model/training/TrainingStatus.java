package ds.project.model.training;

import ds.project.model.student.StudyStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "training_status")
public class TrainingStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "training_status", nullable = false, unique = true)
    private TrainingStatusEnum trainingStatus;

}