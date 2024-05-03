package ds.project.model.person;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "username", nullable = false, unique = true, length = 20)
    private String username;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 30)
    private String email;

    @Column(name = "phone", nullable = false, unique = true, length = 12)
    private String phone;

    @Column(name = "telegram", nullable = false, unique = true, length = 30)
    private String telegram;

    @Column(name = "add_info")
    private String addInfo;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "city", nullable = false, length = 20)
    private String city;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_role_id", nullable = false, columnDefinition = "int default 1") // по умолчанию - USER
    private PersonRole personRole;

}