package generatorevoti.database.repositories;

import generatorevoti.database.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDao extends JpaRepository<Student, String> {

    List<Student> findByClazzAndAcademicYear(String clazz, String academicYear);
}
