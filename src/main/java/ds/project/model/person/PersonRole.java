package ds.project.model.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "person_role")
@NoArgsConstructor
public class PersonRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<Person> persons;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }

    public PersonRole(Integer id) {
        this.id = id;
    }

    public PersonRole(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}