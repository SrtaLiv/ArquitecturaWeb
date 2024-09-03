package Integrador.dao;

import Integrador.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductoDAO {
    private Connection conn;
    public ProductoDAO(Connection conn) {
        this.conn = conn;
    }
    public void insert(Producto producto) throws Exception {
        String query = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, producto.getIdProducto()); // id
            ps.setString(2, producto.getNombre()); // nombre
            ps.setInt(3, producto.getValor()); // valor
            ps.executeUpdate();
            //System.out.println("producto insertado exitosamente.");
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


    public Producto find(Integer pk) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }


    public boolean update(Producto dao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
