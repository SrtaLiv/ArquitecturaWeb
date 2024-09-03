package Integrador.dao;

import Integrador.entities.Factura_Producto;
import Integrador.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Factura_ProductoDAO {
    private Connection conn;
    public Factura_ProductoDAO(Connection conn) {
        this.conn = conn;
    }
    public void insert(Factura_Producto facturaProducto) throws Exception {
        String query = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, facturaProducto.getIdFactura()); // idF
            ps.setInt(2, facturaProducto.getIdProducto()); // idP
            ps.setInt(3, facturaProducto.getCantidad()); // cantidad
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


    public Factura_Producto find(Integer pk) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }


    public boolean update(Factura_Producto dao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
