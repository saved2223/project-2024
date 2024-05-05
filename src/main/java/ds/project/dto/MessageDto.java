package ds.project.dto;

import lombok.Value;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link ds.project.model.Message}
 */
@Value
public class MessageDto implements Serializable {
    String personFromUsername;
    String personToUsername;
    Timestamp timestamp;
    String text;
}