package integrador3.integrador3.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {
    @Id
    private int id_carrera;
    @Column
    private String nombre;
    @Column
    private int duracion;
    @OneToMany(mappedBy = "carrera")
    private List<Estudiante_Carrera> estudiantes;
    @jakarta.persistence.Id
    private Long id;

    public Carrera() {}
    public Carrera(int i,String nombre, int duracion) {
        this.id_carrera = i;
        this.nombre = nombre;
        this.duracion = duracion;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
