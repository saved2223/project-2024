package ds.project.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

@Value
public class TaskForMarkDTO implements Serializable {
    UUID taskId;
    String mark;
    String comment;
    UUID studentID;
}
