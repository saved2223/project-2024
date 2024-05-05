package ds.project.dto.student;

import ds.project.model.student.StudyStatusEnum;
import ds.project.model.training.TrainingStatusEnum;
import lombok.Value;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link ds.project.model.student.Student}
 */
@Value
public class StudentDto implements Serializable {
    String personUsername;
    String personName;
    String personEmail;
    String personPhone;
    String personTelegram;
    String personAddInfo;
    Date personDateOfBirth;
    String personCity;
    String university;
    String departament;
    String speciality;
    Byte course;
    StudyStatusEnum studyStatusStudy_status;
    Set<TrainingDto> trainings;

    /**
     * DTO for {@link ds.project.model.training.Training}
     */
    @Value
    public static class TrainingDto implements Serializable {
        UUID id;
        String name;
        String description;
        Date startDate;
        Date endDate;
        Date registrationDeadline;
        Integer trainingStatusId;
        TrainingStatusEnum trainingStatusTrainingStatus;
    }
}