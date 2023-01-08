package generatorevoti.database.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Valutation {
    @EmbeddedId
    private ValutationId valutationId;
    private String name;
    private String surname;
    private String mark;
}
