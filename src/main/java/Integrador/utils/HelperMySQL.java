package Integrador.utils;

import Integrador.entities.Cliente;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import Integrador.dao.ClienteDAO;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelperMySQL {
    private Connection conn = null;

    public HelperMySQL() {//Constructor
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/arquiDB";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, "root", "root");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        //Funcion que cierra la conexion
        if (conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void dropTables() throws SQLException {
        String dropFactura_Producto = "DROP TABLE IF EXISTS Factura_Producto";
        this.conn.prepareStatement(dropFactura_Producto).execute();
        this.conn.commit();
        String dropFactura = "DROP TABLE IF EXISTS Factura";
        this.conn.prepareStatement(dropFactura).execute();
        this.conn.commit();
        //Funcion que elimina las tablas si existen
        String dropCliente = "DROP TABLE IF EXISTS Cliente";
        this.conn.prepareStatement(dropCliente).execute();
        this.conn.commit();

        String dropProducto = "DROP TABLE IF EXISTS Producto";
        this.conn.prepareStatement(dropProducto).execute();
        this.conn.commit();
    }

    public void createTables() throws SQLException {
        //Funcion que crea las tablas si no existen
        String tablaCliente = "CREATE TABLE IF NOT EXISTS Cliente (" +
                "idCliente INT NOT NULL, " +
                "nombre VARCHAR(500), " +
                "email VARCHAR(150), " +
                "CONSTRAINT Cliente_pk PRIMARY KEY (idCliente))";
        this.conn.prepareStatement(tablaCliente).execute();
        this.conn.commit();
        
        String tablaFactura = "CREATE TABLE IF NOT EXISTS Factura(" +
                "idFactura INT NOT NULL , " +
                "idCliente INT NOT NULL , " +
                "CONSTRAINT Factura_pk PRIMARY KEY (idFactura), " +
                "CONSTRAINT FK_Cliente_fk FOREIGN KEY (idCliente) REFERENCES Cliente (idCliente)) ";
        this.conn.prepareStatement(tablaFactura).execute();
        this.conn.commit();

        String tablaProducto = "CREATE TABLE IF NOT EXISTS Producto(" +
                "idProducto INT NOT NULL , " +
                "nombre VARCHAR(45) , " +
                "valor FLOAT , " +
                "CONSTRAINT Producto_pk PRIMARY KEY (idProducto))";
        this.conn.prepareStatement(tablaProducto).execute();
        this.conn.commit();

        String tablaFactura_Producto = "CREATE TABLE IF NOT EXISTS Factura_Producto (" +
                "idFactura INT NOT NULL , " +
                "idProducto INT NOT NULL , " +
                "cantidad INT, " +
                "CONSTRAINT FK_Factura_fk FOREIGN KEY (idFactura) REFERENCES Factura (idFactura)," +
                "CONSTRAINT FK_Producto_fk FOREIGN KEY (idProducto) REFERENCES Producto (idProducto))";
        this.conn.prepareStatement(tablaFactura_Producto).execute();
        this.conn.commit();
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};  // Puedes configurar tu encabezado personalizado aquí si es necesario
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void populateDB(ClienteDAO clientedao) throws Exception {
        //Metodo que recorre un archivo CSV y inserta los datos en las tablas indicadas
        try {
            System.out.println("Populating DB...");
            for(CSVRecord row : getData("clientes.csv")) {
                //idCliente,nombre,email
                if(row.size() >= 3) { // Verificar que hay al menos 4 campos en el CSVRecord
                    String idString = row.get(0);//obtiene el id de direccion
                    String nombreString = row.get(1);//obtiene el nombre
                    String emailString = row.get(2);//obtiene el email
                    if(!idString.isEmpty() && !nombreString.isEmpty() && !emailString.isEmpty() ) {//si no estan vacios
                        try {
                            int id = Integer.parseInt(idString);//lo parsea a int
                            Cliente cliente = new Cliente(id, nombreString, emailString);
                            clientedao.insertCliente(cliente);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Clientes insertados");

            /*for (CSVRecord row : getData("personas.csv")) {
                if (row.size() >= 4) { // Verificar que hay al menos 4 campos en el CSVRecord
                    String idString = row.get(0);
                    String nombre = row.get(1);
                    String edadString = row.get(2);
                    String idDireccionString = row.get(3);

                    if (!idString.isEmpty() && !nombre.isEmpty() && !edadString.isEmpty() && !idDireccionString.isEmpty()) {
                        try {
                            int id = Integer.parseInt(idString);
                            int edad = Integer.parseInt(edadString);
                            int idDireccion = Integer.parseInt(idDireccionString);

                            Persona persona = new Persona(id, nombre, edad, idDireccion);
                            insertPersona(persona, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de persona: " + e.getMessage());
                        }
                    }
                }
            }*/

            System.out.println("Personas insertadas");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*private int insertPersona (Persona persona, Connection conn) throws Exception{
        String insert = "INSERT INTO Persona (idPersona, nombre, edad,idDireccion) VALUES (?, ?, ?,?)";//crea el string con la sentencia
        PreparedStatement ps = null; //Declara una variable ps de tipo PreparedStatement, que se usará para preparar y ejecutar la consulta SQL.
        try {
            //Preparar y Ejecutar la Consulta:
            ps = conn.prepareStatement(insert);//Usa el objeto conn (que se supone es una conexión a la base de datos) para crear un PreparedStatement con la consulta de inserción.
            //Establecer los Parámetros:
            ps.setInt(1,persona.getIdPersona());
            ps.setString(2, persona.getNombre());
            ps.setInt(3,persona.getEdad());
            ps.setInt(4,persona.getIdDireccion());
            //executeUpdate() ejecuta la consulta SQL y devuelve el número de filas afectadas
            //Si el número de filas afectadas es 0, significa que la inserción no se realizó correctamente
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //cierra el preparedStatement para liberar recursos
        finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }


    private int insertDireccion(Direccion direccion, Connection conn) throws Exception {

        String insert = "INSERT INTO Direccion (idDireccion, ciudad, calle,numero) VALUES (?, ?, ?,?)";//crea el string con la sentencia
        PreparedStatement ps = null;//Declara una variable ps de tipo PreparedStatement, que se usará para preparar y ejecutar la consulta SQL.
        try {
            //Preparar y Ejecutar la Consulta:
            ps = conn.prepareStatement(insert);//Usa el objeto conn (que se supone es una conexión a la base de datos) para crear un PreparedStatement con la consulta de inserción.
            //Establecer los Parámetros:
            ps.setInt(1,direccion.getIdDireccion());
            ps.setString(2, direccion.getCiudad());
            ps.setString(3,direccion.getCalle());
            ps.setInt(4,direccion.getNumero());
            //executeUpdate() ejecuta la consulta SQL y devuelve el número de filas afectadas
            //Si el número de filas afectadas es 0, significa que la inserción no se realizó correctamente
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //cierra el preparedStatement para liberar recursos
        finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }*/
    private void closePsAndCommit(Connection conn, PreparedStatement ps) {
        //Funcion que cierra el PreparedStatement y commitea
        if (conn != null){
            try {
                ps.close();
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


