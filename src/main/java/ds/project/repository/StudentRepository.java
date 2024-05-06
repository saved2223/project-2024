package ds.project.repository;

import ds.project.model.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByPerson_Username(String username);

    List<Student> findByTrainings_Id(UUID id);


}