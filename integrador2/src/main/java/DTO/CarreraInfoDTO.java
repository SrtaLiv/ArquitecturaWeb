package DTO;

public class CarreraInfoDTO {
    private int inscriptos;
    private int egresados;

    public CarreraInfoDTO(int inscriptos, int egresados){
        this.inscriptos = inscriptos;
        this.egresados = egresados;
    }

    @Override
    public String toString() {
        return "inscriptos = " + inscriptos +
                ", egresados = " + egresados;
    }
}
