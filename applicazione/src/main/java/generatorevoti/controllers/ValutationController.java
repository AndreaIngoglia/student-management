package generatorevoti.controllers;

import generatorevoti.database.entities.Valutation;
import generatorevoti.services.ValutationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ValutationController {
    private ValutationService valutationService;

    @GetMapping(path = {"/valutations"})
    public List<Valutation> findAll(){
        return valutationService.findAll();
    }
}
