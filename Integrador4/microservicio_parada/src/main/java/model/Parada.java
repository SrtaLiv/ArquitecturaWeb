package model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter
@Setter
public class Parada {
    @Id
    private int id;
    int latitud;
    int longitud;
    int monopatin_id;
    int km_acumulados;
}
