package ds.project.dto;

import ds.project.model.training.Training;
import lombok.Value;

import java.io.Serializable;
import java.sql.Date;

/**
 * DTO for {@link Training}
 */
@Value
public class TrainingCreationDto implements Serializable {
    String name;
    String description;
    Date startDate;
    Date endDate;
    Date registrationDeadline;
}