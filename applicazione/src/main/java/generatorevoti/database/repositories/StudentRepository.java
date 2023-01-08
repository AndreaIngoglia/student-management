package generatorevoti.database.repositories;

import generatorevoti.database.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, String> {

    List<StudentEntity> findByClazz(String clazz);
}