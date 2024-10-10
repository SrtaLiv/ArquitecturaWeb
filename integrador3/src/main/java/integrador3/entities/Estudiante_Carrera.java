package integrador3.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Estudiante_Carrera implements Serializable {
//    @EmbeddedId
//    private Estudiante_Carrera_pk pk;
    @Id
    private int id;
    @ManyToOne
    private Estudiante estudiante;
    @ManyToOne
    private Carrera carrera;
    @Column
    private int anio_inicio;
    @Column
    private int anio_fin;
    @Column
    private int antiguedad;

    public Estudiante_Carrera() {}

    public Estudiante_Carrera(int id, Estudiante e, Carrera c, int anio_inicio, int anio_fin, int antiguedad) {
        this.id = id;
        this.estudiante = e;
        this.carrera=c;
        this.anio_inicio = anio_inicio;
        this.anio_fin = anio_fin;
        this.antiguedad = antiguedad;
//        this.pk = new Estudiante_Carrera_pk(c.getId_carrera(),e.getDni());
    }
}
