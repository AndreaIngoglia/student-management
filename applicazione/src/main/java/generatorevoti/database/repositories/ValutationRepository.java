package generatorevoti.database.repositories;

import generatorevoti.database.entities.ValutationEntity;
import generatorevoti.database.entities.ValutationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValutationRepository extends JpaRepository<ValutationEntity, ValutationId> {
    List<ValutationEntity> findByValutationIdEmailAndValutationIdSubject(String email, String subject);
}