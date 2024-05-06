package ds.project.repository;

import ds.project.model.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    Person findByUsername(String username);

    List<Person> findByRoles_Name(String name);

}