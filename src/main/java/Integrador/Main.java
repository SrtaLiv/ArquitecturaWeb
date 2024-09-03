package Integrador;

import Integrador.dao.ClienteDAO;
import Integrador.dao.FacturaDAO;
import Integrador.dao.Factura_ProductoDAO;
import Integrador.dao.ProductoDAO;
import Integrador.dto.PersonaDTO;
import Integrador.factory.AbstractFactory;
import Integrador.utils.HelperMySQL;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
        ClienteDAO cliente = chosenFactory.getClienteDAO();
        Factura_ProductoDAO factura_producto = chosenFactory.getFactura_ProductoDAO();
        FacturaDAO factura = chosenFactory.getFacturaDAO();
        ProductoDAO producto = chosenFactory.getProductoDAO();

        HelperMySQL dbMySQL = new HelperMySQL();

        dbMySQL.dropTables();
        dbMySQL.createTables();
        dbMySQL.populateDB(cliente,producto,factura_producto,factura);
        dbMySQL.closeConnection();

        System.out.println();
        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");
        System.out.println();

        /*System.out.println("Busco una Persona por id: ");
        Persona personaById = persona.find(2);
        System.out.println(personaById);
        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");
        System.out.println("Lista de direcciones: ");
//        List<Direccion> listadoDirecciones = direccion.selectList();
//        System.out.println(listadoDirecciones);
        List<Direccion> listadoDirecciones = direccion.selectList();
        for (Direccion dir : listadoDirecciones) {
            System.out.println(dir);
        }*/

        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");

//        Persona p = new Persona(6,"Sergio",50,2);
//        persona.insertPersona(p);

//        PersonaDTO personaDTO = persona.findPersonaDTO(2);
//        System.out.println(personaDTO);

    }
}
