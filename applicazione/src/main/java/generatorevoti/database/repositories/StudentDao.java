package generatorevoti.database.repositories;

import generatorevoti.database.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student, String> {
}
