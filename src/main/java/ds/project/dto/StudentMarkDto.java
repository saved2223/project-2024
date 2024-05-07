package ds.project.dto;

import lombok.Value;

import java.io.Serializable;

@Value
public class StudentMarkDto implements Serializable {
    String taskName;
    String result;
}
