package ds.project.dto.student;

import ds.project.model.student.StudyStatusEnum;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ds.project.model.student.Student}
 */
@Value
public class StudentRegisterDto implements Serializable {
    String university;
    String departament;
    String speciality;
    Byte course;
    StudyStatusEnum studyStatusStudy_status;
}