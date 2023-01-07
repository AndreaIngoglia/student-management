package generatorevoti.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    @Id
    private String email;
    private String name;
    private String surname;
    private String subject;
    private String mark;
    private String date;
}
