package ds.project.dto;

import ds.project.model.training.Training;
import ds.project.model.training.TrainingStatus;
import lombok.Value;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

/**
 * DTO for {@link Training}
 */
@Value
public class TrainingDto implements Serializable {
    String name;
    String description;
    Date startDate;
    Date endDate;
    Date registrationDeadline;
}