package DTO;

import entities.Carrera;
import entities.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class CarreraInfoDTO {
    private int inscriptos;
    private int egresados;

    public CarreraInfoDTO(int inscriptos, int egresados){
        this.inscriptos = inscriptos;
        this.egresados = egresados;
    }

    @Override
    public String toString() {
        return "CarreraInfoDTO{" +
                "inscriptos=" + inscriptos +
                ", egresados=" + egresados +
                '}';
    }
}
