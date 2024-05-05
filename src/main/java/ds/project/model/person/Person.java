package ds.project.model.person;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person implements UserDetails {

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

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<PersonRole> roles;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}