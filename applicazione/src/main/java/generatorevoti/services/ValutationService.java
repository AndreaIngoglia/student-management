package generatorevoti.services;

import generatorevoti.database.entities.Valutation;
import generatorevoti.database.repositories.ValutationDao;
import generatorevoti.utils.ValutationStatsPanel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ValutationService {
    private ValutationDao valutationDao;

    public List<Valutation> findAll() {
        return valutationDao.findAll();
    }
    public Valutation save(Valutation valutation) {
        return valutationDao.save(valutation);
    }
    public List<Valutation> findByClazzAndSubjectAndDateAndAcademicYear(String clazz, String subject, String date, String academicYear) {
        return valutationDao.findAll().stream()
                .filter(v -> clazz.equalsIgnoreCase(v.getClazz()) && subject.equalsIgnoreCase(v.getValutationId().getSubject())
                        && date.equalsIgnoreCase(v.getValutationId().getDate()) && academicYear.equalsIgnoreCase(v.getAcademicYear()))
                .collect(Collectors.toList());
    }
    public List<Valutation> findByNameAndSurnameAndEmailAndAcademicYearAndClazzAndSubject(ValutationStatsPanel statsPanel){
        return valutationDao.findByValutationIdEmailAndNameAndSurnameAndAcademicYearAndClazzAndValutationIdSubject(
                statsPanel.getEmail(),statsPanel.getName(), statsPanel.getSurname(), statsPanel.getAcademicYear(), statsPanel.getClazz(), statsPanel.getSubject());
    }
}