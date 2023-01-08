package generatorevoti.database.repositories;

import generatorevoti.database.entities.Valutation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValutationDao extends JpaRepository<Valutation, String> {
    List<Valutation> findByValutationIdEmailAndNameAndSurnameAndAcademicYearAndClazzAndValutationIdSubject(String email, String name, String surname, String academicYear, String clazz, String subject);
}