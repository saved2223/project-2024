package ds.project.dto;

import ds.project.model.person.Person;
import lombok.Value;

import java.io.Serializable;
import java.sql.Date;

/**
 * DTO for {@link Person}
 */
@Value
public class PersonRegisterDto implements Serializable {
    String username;
    String name;
    String email;
    String phone;
    String telegram;
    String addInfo;
    Date dateOfBirth;
    String city;
    String password;
}