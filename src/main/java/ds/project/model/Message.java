package ds.project.model;

import ds.project.model.person.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "person_from_id", nullable = true)
    private Person personFrom;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_to_id", nullable = false)
    private Person personTo;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    @Lob
    @Column(name = "text", nullable = false)
    private String text;

}