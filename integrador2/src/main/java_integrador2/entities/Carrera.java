package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {
    @Id
    private int id_carrera;
    private String nombre;
    @OneToMany(mappedBy = "carrera")
    private List<Estudiante_Carrera> estudiantes;

    public Carrera() {}
    public Carrera(String nombre) {
        this.nombre = nombre;
        this.estudiantes = new ArrayList<Estudiante_Carrera>();
    }

}
