package ds.project.model.training;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "training")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "registration_deadline", nullable = false)
    private Date registrationDeadline;

    @ManyToOne(optional = false)
    @JoinColumn(name = "training_status_id", nullable = false)
    private TrainingStatus trainingStatus;

}