package generatorevoti.database.repositories;

import generatorevoti.database.entities.Valutation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValutationDao extends JpaRepository<Valutation, String> {
}