package entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Estudiante_Carrera_pk implements Serializable {
    private int id_carrera;
    private int id_estudiante;


}
