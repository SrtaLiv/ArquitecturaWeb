package helper;

import entities.Carrera;
import entities.Estudiante;
import entities.Estudiante_Carrera;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import repositories.CarreraRepository;
import repositories.EstudianteRepository;
import repositories.Estudiante_CarreraRepository;

import javax.persistence.EntityManager;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Date;

public class CSVReader {
    private EntityManager em;
    private CarreraRepository cr;
    private EstudianteRepository er;
    private Estudiante_CarreraRepository ecr;
    public CSVReader(EntityManager em, CarreraRepository cr, Estudiante_CarreraRepository ecr, EstudianteRepository er){
        this.em = em;
        this.cr = cr;
        this.er = er;
        this.ecr = ecr;
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "integrador2\\src\\main\\resources\\" + archivo;
        // String path = "integrador2/src/main/resources/" + archivo; //para linux
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void populateDB() throws Exception {
        try {
            em.getTransaction().begin();
            insertEstudiantes();
            insertCarreras();
            insertMatriculas();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertEstudiantes() throws IOException {
        for(CSVRecord row : getData("estudiantes.csv")) {
            //nroLU,nombre,apellido,edad,sexo,dni,ciudad
            if(row.size() >= 7) {
                String nroLUString = row.get(0);
                String nombre = row.get(1);
                String apellido = row.get(2);
                String edadString = row.get(3);
                String sexo = row.get(4);
                String dniString = row.get(5);
                String ciudad = row.get(6);
                if(!nroLUString.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !edadString.isEmpty() && !sexo.isEmpty() && !dniString.isEmpty() && !ciudad.isEmpty()) {
                    try {
                        int nroLU = Integer.parseInt(nroLUString);
                        int edad = Integer.parseInt(edadString);
                        int dni = Integer.parseInt(dniString);
                        Estudiante estudiante = new Estudiante(nroLU, nombre, apellido, edad, sexo, dni, ciudad);
                        er.create(estudiante);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                    }
                }
            }
        }
    }
    private void insertCarreras() throws IOException {
        for(CSVRecord row : getData("carreras.csv")) {
            //id_carrera,nombre
            if(row.size() >= 2) {
                String id_carreraString = row.get(0);
                String nombre = row.get(1);
                if(!id_carreraString.isEmpty() && !nombre.isEmpty()) {
                    try {
                        int id_carrera = Integer.parseInt(id_carreraString);
                        Carrera carrera = new Carrera(id_carrera, nombre);
                        cr.create(carrera);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                    }
                }
            }
        }
    }
    private void insertMatriculas() throws IOException {
        for(CSVRecord row : getData("matriculas.csv")) {
            //nroLu,id_carrera,fecha_inicio,fecha_fin
            if(row.size() >= 4) {
                String nroLuString = row.get(0);
                String id_carreraString = row.get(1);
                String fecha_inicioString = row.get(2);
                String fecha_finString = row.get(3);
                if(!nroLuString.isEmpty() && !id_carreraString.isEmpty() && !fecha_inicioString.isEmpty() && !fecha_finString.isEmpty()) {
                    try {
                        int nroLu = Integer.parseInt(nroLuString);
                        int id_carrera = Integer.parseInt(id_carreraString);
                        long fecha_inicioL = Long.parseLong(fecha_inicioString);
                        Date fecha_inicio = new Date(fecha_inicioL);
                        Date fecha_fin = null;
                        if (!fecha_finString.equals("null")){
                            long fecha_finL = Long.parseLong(fecha_finString);
                            fecha_fin = new Date(fecha_finL);
                        }
                        Estudiante estudiante = er.findById(nroLu);
                        Carrera carrera = cr.findById(id_carrera);
                        Estudiante_Carrera estudianteCarrera = new Estudiante_Carrera(estudiante, carrera, fecha_inicio, fecha_fin);
                        ecr.create(estudianteCarrera);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                    }
                }
            }
        }
    }
}
