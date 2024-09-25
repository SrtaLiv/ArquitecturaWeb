package entities;



import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Estudiante_Carrera implements Serializable {
    @EmbeddedId
    private Estudiante_Carrera_pk pk;


    @ManyToOne
    @MapsId("id_estudiante")
    private Estudiante estudiante;

    @ManyToOne
    @MapsId("id_carrera")
    private Carrera carrera;
    @Column
    private Date fecha_inicio;
    @Column
    private Date fecha_fin;


    public Estudiante_Carrera() {}

    public Estudiante_Carrera(Estudiante e,Carrera c, String carrera_nombre, Date fecha_inicio, Date fecha_fin) {
        this.estudiante = e;
        this.carrera=c;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }
}
