package integrador3.jsonEntities;

import lombok.Data;

@Data
public class Estudiante_CarreraJSON {
    private int id;
    private int id_estudiante;
    private int id_carrera;
    private int anio_inicio;
    private int anio_fin;
    private int antiguedad;

    public Estudiante_CarreraJSON() {
    }

//    public int getId_estudiante() {
//        return id_estudiante;
//    }
//
//    public int getId_carrera() {
//        return id_carrera;
//    }
//
//    public int getAnio_inicio() {
//        return anio_inicio;
//    }
//
//    public int getAnio_fin() {
//        return anio_fin;
//    }
//
//    public int getAntiguedad() {
//        return antiguedad;
//    }
}
