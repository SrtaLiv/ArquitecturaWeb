package integrador3.DTO;

import lombok.Getter;

@Getter
public class CarreraInfoDTO {
    private int inscriptos;
    private int egresados;

    public CarreraInfoDTO(int inscriptos){
        this.inscriptos = inscriptos;
        this.egresados = 0;
    }

    public void setEgresados(int egresados) {
        this.egresados = egresados;
    }

    @Override
    public String toString() {
        return "inscriptos = " + inscriptos +
                ", egresados = " + egresados;
    }
}
