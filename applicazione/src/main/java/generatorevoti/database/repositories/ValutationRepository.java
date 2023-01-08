package generatorevoti.database.repositories;

import generatorevoti.database.entities.ValutationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValutationRepository extends JpaRepository<ValutationEntity, String> {
    List<ValutationEntity> findByValutationIdEmailAndValutationIdSubject(String email, String subject);
}