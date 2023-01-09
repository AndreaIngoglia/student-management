package generatorevoti.services;

import generatorevoti.database.entities.ValutationEntity;
import generatorevoti.database.entities.ValutationId;
import generatorevoti.database.repositories.ValutationRepository;
import generatorevoti.utils.StatsVisualization;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ValutationService {
    private ValutationRepository valutationRepository;

    public ValutationEntity save(ValutationEntity valutation) {
        return valutationRepository.save(valutation);
    }
    public List<ValutationEntity> findByClazzAndSubjectAndDate(String clazz, String subject, String date) {
        return valutationRepository
                .findAll()
                .stream()
                .filter(v ->
                            clazz.equalsIgnoreCase(v.getClazz()) &&
                            subject.equalsIgnoreCase(v.getValutationId().getSubject()) &&
                            date.equalsIgnoreCase(v.getValutationId().getDate()))
                .collect(Collectors.toList());
    }
    public List<ValutationEntity> findByEmailAndSubject(StatsVisualization statsVisualization){
        return valutationRepository
                .findByValutationIdEmailAndValutationIdSubject(statsVisualization.getEmail(), statsVisualization.getSubject());
    }
    public boolean alreadyExists(ValutationId id){
        return valutationRepository.existsById(id);
    }
}