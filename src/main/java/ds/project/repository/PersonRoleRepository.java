package ds.project.repository;

import ds.project.model.person.PersonRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRoleRepository extends JpaRepository<PersonRole, Integer> {
  Optional<PersonRole> findByName(String name);
}