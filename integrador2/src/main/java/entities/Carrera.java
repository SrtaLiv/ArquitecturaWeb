package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {
    @Id
    private int id_carrera;
    @Column
    private String nombre;
    @OneToMany(mappedBy = "carrera")
    private List<Estudiante_Carrera> estudiantes;

    public Carrera() {}
    public Carrera(int i,String nombre) {
        this.id_carrera = i;
        this.nombre = nombre;
        this.estudiantes = new ArrayList<Estudiante_Carrera>();
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "id_carrera=" + id_carrera +
                ", nombre='" + nombre +
                '}';
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public String getNombre() {
        return nombre;
    }
}
