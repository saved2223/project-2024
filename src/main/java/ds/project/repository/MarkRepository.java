package ds.project.repository;

import ds.project.model.mark.Mark;
import ds.project.model.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MarkRepository extends JpaRepository<Mark, UUID> {
    List<Mark> findByStudent_Trainings_IdAndStudent_Person(UUID id, Person person);
}