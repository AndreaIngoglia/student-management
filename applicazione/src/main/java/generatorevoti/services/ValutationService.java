package generatorevoti.services;

import generatorevoti.database.entities.Valutation;
import generatorevoti.database.repositories.ValutationDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ValutationService {
    private ValutationDao valutationDao;

    public List<Valutation> findAll(){
        return valutationDao.findAll();
    }

    public Valutation save(Valutation valutation){
        return valutationDao.save(valutation);
    }
}
