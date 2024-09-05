package Integrador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Integrador.dto.ClienteDTO;
import Integrador.dto.PersonaDTO;
import Integrador.entities.Cliente;
import Integrador.entities.Producto;

public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertCliente(Cliente cliente) {
        String query = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, cliente.getIdCliente()); // idPersona
            ps.setString(2, cliente.getNombre()); // nombre
            ps.setString(3, cliente.getEmail()); // edad
            ps.executeUpdate();
            //System.out.println("Persona insertada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    public Cliente find(Integer pk) {
        String query = "SELECT c.nombre, c.email " +
                "FROM Cliente c " +
                "WHERE c.idCliente = ?";
        Cliente clienteById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, pk); // Establecer el parámetro en la consulta SQL
            rs = ps.executeQuery();
            if (rs.next()) { // Verificar si hay resultados
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");

                // Crear una nueva instancia de Persona con los datos recuperados de la consulta
                clienteById = new Cliente(pk, nombre, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return clienteById;
        }
    public List<ClienteDTO> findByMasFacturado() {
        String sql = "SELECT c.idCliente, \n" +
                "       c.nombre, \n" +
                "       c.email, \n" +
                "       COALESCE(SUM(p.valor * fp.cantidad), 0) AS totalFacturado\n" +
                "FROM Cliente c\n" +
                "LEFT JOIN Factura f ON c.idCliente = f.idCliente\n" +
                "LEFT JOIN Factura_Producto fp ON f.idFactura = fp.idFactura\n" +
                "LEFT JOIN Producto p ON fp.idProducto = p.idProducto\n" +
                "GROUP BY c.idCliente, c.nombre, c.email\n" +//Agrupa los resultados por cliente para sumar el monto total facturado a cada uno.
                "ORDER BY totalFacturado DESC;";
        Cliente clienteById = null;
        int totalFacturado = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ClienteDTO> list = null;
        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            list = new ArrayList<ClienteDTO>();
            while (rs.next()) {
                int facturado = rs.getInt("totalFacturado");
                int idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                ClienteDTO cliente = new ClienteDTO(facturado,idCliente,nombre);
                list.add(cliente);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
