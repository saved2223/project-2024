package ds.project.dto;

import lombok.Value;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Value
public class CheckLessonTasksDTO implements Serializable {
    UUID taskId;
    String taskName;
    String userID;
    String username;
    Date lastCommitDate;
    String commitAuthorUsername;
    String commitLink;
}
