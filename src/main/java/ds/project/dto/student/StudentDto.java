package ds.project.dto.student;

import ds.project.model.student.StudyStatusEnum;
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
    Set<UUID> trainingIds;
}